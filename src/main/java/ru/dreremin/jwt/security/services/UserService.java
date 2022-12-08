package ru.dreremin.jwt.security.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.dreremin.jwt.security.dto.request.AuthenticationDto;
import ru.dreremin.jwt.security.dto.request.CreateUserDto;
import ru.dreremin.jwt.security.dto.request.DeleteUserDto;
import ru.dreremin.jwt.security.dto.request.LoginDto;
import ru.dreremin.jwt.security.exceptions.UniquenessViolationException;
import ru.dreremin.jwt.security.models.Actor;
import ru.dreremin.jwt.security.models.Person;
import ru.dreremin.jwt.security.repositories.ActorRepository;
import ru.dreremin.jwt.security.repositories.PersonRepository;
import ru.dreremin.jwt.security.security.ActorDetails;
import ru.dreremin.jwt.security.security.JwtTokenProvider;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

	@Autowired
	private final ActorRepository actorRepo;
	
	@Autowired
	private final PersonRepository personRepo;
	
	@Autowired
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private final JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private final ActorDetailsService actorDetailsService;
	
	@Autowired
	private final AuthenticationManager authenticationManager;
	
	public String createUser(CreateUserDto dto) {
		
		Optional<Actor> actorOpt = actorRepo.findByLogin(dto.getLogin());
		
		if (actorOpt.isPresent()) {
			throw new UniquenessViolationException(
					"User with this login already exists");
		}
		String encodedPassword = passwordEncoder.encode(dto.getPassword());
		Actor actor = actorRepo.save(new Actor(
				dto.getLogin(), 
				encodedPassword,   //в БД сохраняем зашифрованный пароль
				"ROLE_USER"));
		personRepo.save(new Person(
				dto.getFirstName(), 
				dto.getLastName(), 
				dto.getBirthday(), 
				actor.getId()));
		return jwtTokenProvider.generateToken(dto.getLogin());
	}
	
	public void deleteUser(DeleteUserDto dto) {
		Optional<Actor> actorOpt = actorRepo.findByLogin(dto.getLogin());
		if (actorOpt.isEmpty()) {
			throw new EntityNotFoundException(
					"User with this login does not exist");
		}
		actorRepo.delete(actorOpt.get());
	}
	
	public String login(AuthenticationDto dto) {

		UsernamePasswordAuthenticationToken authToken = 
				new UsernamePasswordAuthenticationToken(
						dto.getLogin(), 
						dto.getPassword());
		
		authenticationManager.authenticate(authToken);
		return jwtTokenProvider.generateToken(dto.getLogin());
	}
}
