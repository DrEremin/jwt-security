package ru.dreremin.jwt.security.controllers;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.dreremin.jwt.security.dto.request.CreateCustomDto;
import ru.dreremin.jwt.security.dto.request.DeleteCustomDto;
import ru.dreremin.jwt.security.dto.request.GetCustomsDto;
import ru.dreremin.jwt.security.dto.response.CustomDto;
import ru.dreremin.jwt.security.dto.response.StatusDto;
import ru.dreremin.jwt.security.models.Custom;
import ru.dreremin.jwt.security.security.ActorDetails;
import ru.dreremin.jwt.security.services.CustomService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customs")
public class CustomController {
	
	@Autowired
	private final CustomService service;

	@PutMapping("/create")
	public ResponseEntity<StatusDto> createCustom(
			@Valid @RequestBody CreateCustomDto dto) {
		
		service.createCustom(dto);
		return new ResponseEntity<>(new StatusDto(200, "Ok"), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<StatusDto> deleteCustom(
			@Valid @RequestBody DeleteCustomDto dto) {
		
		service.deleteCustom(dto);
		return new ResponseEntity<>(new StatusDto(200, "Ok"), HttpStatus.OK);
	} 
	/*
	@PostMapping("/get")
	public ResponseEntity<List<CustomDto>> getCustoms(
			@Valid @RequestBody GetCustomsDto dto) {
		
		List<Custom> customs = service.getCustoms(dto);
		LinkedList<CustomDto> customDtos = new LinkedList<>(); 
		
		for (Custom custom : customs) {
			customDtos.add(new CustomDto(
					custom.getId(), 
					custom.getName(), 
					custom.getAmount(), 
					custom.getActorId()));
		}
		return new ResponseEntity<>(customDtos, HttpStatus.OK);
	}*/
	@GetMapping("/get")
	public ResponseEntity<List<CustomDto>> getCustoms() {
		
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		ActorDetails actorDetails = (ActorDetails) authentication
				.getPrincipal();
		
		List<Custom> customs = service.getCustoms(actorDetails.getActor());
		LinkedList<CustomDto> customDtos = new LinkedList<>(); 
		
		for (Custom custom : customs) {
			customDtos.add(new CustomDto(
					custom.getId(), 
					custom.getName(), 
					custom.getAmount(), 
					custom.getActorId()));
		}
		return new ResponseEntity<>(customDtos, HttpStatus.OK);
	}
}
