package ru.dreremin.jwt.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.dreremin.jwt.security.models.Actor;
import ru.dreremin.jwt.security.repositories.ActorRepository;
import ru.dreremin.jwt.security.security.ActorDetails;

@RequiredArgsConstructor
@Service
public class ActorDetailsService implements UserDetailsService {

	@Autowired
	private final ActorRepository actorRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		Optional<Actor> actorOpt = actorRepo.findByLogin(username);
		if (actorOpt.isEmpty()) {
			throw new UsernameNotFoundException(
					"User with this login does not exist");
		}
		
		return new ActorDetails(actorOpt.get());
	}

}
