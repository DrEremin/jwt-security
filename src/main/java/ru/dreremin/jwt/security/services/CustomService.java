package ru.dreremin.jwt.security.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.dreremin.jwt.security.dto.request.CreateCustomDto;
import ru.dreremin.jwt.security.dto.request.DeleteCustomDto;
import ru.dreremin.jwt.security.dto.request.GetCustomsDto;
import ru.dreremin.jwt.security.models.Actor;
import ru.dreremin.jwt.security.models.Custom;
import ru.dreremin.jwt.security.repositories.ActorRepository;
import ru.dreremin.jwt.security.repositories.CustomRepository;

@RequiredArgsConstructor
@Service
public class CustomService {

	@Autowired
	private final CustomRepository customRepo;
	
	@Autowired
	private final ActorRepository actorRepo;
	
	public List<Custom> getCustoms(Actor actor) {
		/*
		Optional<Actor> actorOpt = actorRepo.findByLogin(actor.getLogin());
		
		if (actorOpt.isEmpty()) {
			throw new EntityNotFoundException(
					"User with this login does not exist");
		}*/
	
		List<Custom> customs = customRepo
				.findByActorId(actor.getId());
		
		if (customs.size() == 0) {
			throw new EntityNotFoundException(
					"User with this login has no customs");
		}
		return customs;
	}
	
	public void createCustom(CreateCustomDto dto) {
		
		Optional<Actor> actorOpt = actorRepo.findByLogin(dto.getLogin());
		
		if (actorOpt.isEmpty()) {
			throw new EntityNotFoundException(
					"User with this login does not exist");
		}
		
		customRepo.save(new Custom(
				dto.getName(), 
				dto.getAmount(),
				actorOpt.get().getId()));
	}
	
	public void deleteCustom(DeleteCustomDto dto) {
		
		Optional<Actor> actorOpt = actorRepo.findByLogin(dto.getLogin());
		
		if (actorOpt.isEmpty()) {
			throw new EntityNotFoundException(
					"User with this login does not exist");
		}
		
		Optional<Custom> customOpt = customRepo.findById(dto.getNumber());
		
		if (customOpt.isEmpty()) {
			throw new EntityNotFoundException(
					"There is no custom with this number");
		}
		
		if (customOpt.get().getActorId() != actorOpt.get().getId()) {
			throw new EntityNotFoundException(
					"User with this login has not such custom");
		}
		
		customRepo.delete(customOpt.get());
	}
}
