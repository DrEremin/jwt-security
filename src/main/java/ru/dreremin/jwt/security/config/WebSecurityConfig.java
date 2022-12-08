package ru.dreremin.jwt.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import ru.dreremin.jwt.security.services.ActorDetailsService;

@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private final ActorDetailsService actorDetailsService;
	
	@Autowired
	private final JwtFilter jwtFilter;
	
	//настройка аутентификации
	@Override
	protected void configure(AuthenticationManagerBuilder auth) 
			throws Exception {
		auth.userDetailsService(actorDetailsService)
				/* Это для того, чтобы Spring Security прогонял входящий пароль 
				 * пользователя через passwordEncoder ведь в БД хранятся 
				 * зашифрованные пароли. Мы сюда передаем бин,
				 * который создали ниже.		
				 */
				.passwordEncoder(getPasswordEncoder());
	}
	
	//настройка авторизации
	//настройка Spring Security
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()                             //когда работаем не с браузером отключаем csrf
				.authorizeRequests()
				.antMatchers("/customs/**").hasAnyRole("USER", "ADMIN") //доступ только для USER и ADMIN
				.antMatchers("/users/delete").hasAnyRole("ADMIN") //доступ только для USER и ADMIN
				.antMatchers("/users/create", "/users/login").permitAll() //доступ для всех
				.and()
				.formLogin().disable()                    //отключаем стандартную форму spring security
				.logout()
				.logoutUrl("/logout")
				.and()
				.sessionManagement()
				//говорим Spring Security чтобы он больше не сохранял сессии на сервере
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//добавляем JwtFilter в цепочку фильтров
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	/*
	@Bean
    AuthenticationManager authenticationManager(
    		AuthenticationConfiguration authenticationConfiguration) 
    				throws Exception { 
        return authenticationConfiguration.getAuthenticationManager();
    }*/
}
