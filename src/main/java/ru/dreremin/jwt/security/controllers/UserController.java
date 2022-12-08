package ru.dreremin.jwt.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.dreremin.jwt.security.dto.request.AuthenticationDto;
import ru.dreremin.jwt.security.dto.request.CreateUserDto;
import ru.dreremin.jwt.security.dto.request.DeleteUserDto;
import ru.dreremin.jwt.security.dto.request.LoginDto;
import ru.dreremin.jwt.security.dto.response.JwtTokenDto;
import ru.dreremin.jwt.security.dto.response.StatusDto;
import ru.dreremin.jwt.security.services.UserService;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private final UserService service;
			
	@PutMapping("/create")
	public ResponseEntity<JwtTokenDto> createUser(
			@Valid @RequestBody CreateUserDto dto) {
		
		String jwtToken = service.createUser(dto);
		
		return new ResponseEntity<>(new JwtTokenDto(jwtToken), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<StatusDto> deleteUser(
			@Valid @RequestBody DeleteUserDto dto) {
		
		service.deleteUser(dto);
		
		return new ResponseEntity<>(new StatusDto(200, "Ok"), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtTokenDto> login(
			@Valid @RequestBody AuthenticationDto dto) {
		String jwtToken = service.login(dto);
		
		return new ResponseEntity<>(new JwtTokenDto(jwtToken), HttpStatus.OK);
	}
}
