package ru.dreremin.jwt.security.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.dreremin.jwt.security.models.Actor;

@RequiredArgsConstructor
@Getter
public class ActorDetails implements UserDetails {

	private final Actor actor;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(
				new SimpleGrantedAuthority(actor.getRole()));
	}

	@Override
	public String getPassword() { return actor.getPassword(); }

	@Override
	public String getUsername() { return actor.getLogin(); }

	@Override
	public boolean isAccountNonExpired() { //аккаунт не просрочен
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {  //аккаунт не заблокирован
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { //пароль не просрочен
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() { //аккаунт включен
		// TODO Auto-generated method stub
		return true;
	}

}
