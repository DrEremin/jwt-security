package ru.dreremin.jwt.security.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;

import lombok.RequiredArgsConstructor;
import ru.dreremin.jwt.security.exceptions.InvalidJwtTokenException;
import ru.dreremin.jwt.security.security.ActorDetails;
import ru.dreremin.jwt.security.security.JwtTokenProvider;
import ru.dreremin.jwt.security.services.ActorDetailsService;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private final JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private final ActorDetailsService actorDetailsService;

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		
		if (authHeader != null 
				&& !authHeader.isBlank() 
				&& authHeader.startsWith("Bearer ")) {
			
			String jwt = authHeader.substring(7); //выделяем непосредственно строку токена
			
			if (jwt.isBlank()) {
				throw new InvalidJwtTokenException(
						"Invalid JWT token in Bearer Header");
			} else {
				try {
					String login = jwtTokenProvider
							//подтверждаем, что токен выдан нами и не был кем-то модифицирован
							.validateTokenAndRetrieveClaim(jwt);
					/* ниже создаем токен аутенитификации и кладем его в контекст
					 * теперь этот аккаунт будет аутентифицирован
					 */
					UserDetails userDetails = actorDetailsService
							.loadUserByUsername(login);
					UsernamePasswordAuthenticationToken authToken = 
							new UsernamePasswordAuthenticationToken(
									userDetails, 
									userDetails.getPassword(), 
									userDetails.getAuthorities());
					if (SecurityContextHolder.getContext().getAuthentication() 
							== null) {
						SecurityContextHolder.getContext()
								.setAuthentication(authToken);
					}
				} catch (JWTVerificationException e) {
					throw new JWTVerificationException("Invalid JWT token");
				}
			}
		} /*else {
			throw new InvalidJwtTokenException(
					"Invalid JWT token in Bearer Header");
		}*/
		//продвигаем запрос дальше по цепочке фильтров
		filterChain.doFilter(request, response);
	}

	/*
	 * eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBY3RvciBkZXRhaWxzIiwiaXNzIjoiand0LXNlY3VyaXR5IiwibG9naW4iOiJhcnR1ciIsImV4cCI6MTY3MDUzMDU0NCwiaWF0IjoxNjcwNTI2OTQ0fQ.Lf4C0bQVNssJ29oyymULhsr8BFW__cNM0MwkEhpOIdE
	 */
}
