package ru.dreremin.jwt.security.controllers;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.auth0.jwt.exceptions.JWTVerificationException;

import ru.dreremin.jwt.security.dto.response.StatusDto;
import ru.dreremin.jwt.security.exceptions.InvalidJwtTokenException;
import ru.dreremin.jwt.security.exceptions.UniquenessViolationException;

@ControllerAdvice
public class ExceptionsController {

	@ExceptionHandler(UniquenessViolationException.class)
	public ResponseEntity<StatusDto> handleUniquenessViolationException(
			UniquenessViolationException e) {
		return new ResponseEntity<>(new StatusDto(409, e.getMessage()), 
				HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StatusDto> handleEntityNotFoundException(
			EntityNotFoundException e) {
		return new ResponseEntity<>(new StatusDto(409, e.getMessage()), 
				HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(InvalidJwtTokenException.class)
	public ResponseEntity<StatusDto> handleInvalidJwtTokenException(
			InvalidJwtTokenException e) {
		return new ResponseEntity<>(new StatusDto(400, e.getMessage()), 
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(JWTVerificationException.class)
	public ResponseEntity<StatusDto> handleInvalidJwtTokenException(
			JWTVerificationException e) {
		return new ResponseEntity<>(new StatusDto(400, e.getMessage()), 
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<StatusDto> handleBadCredentialsException(
			BadCredentialsException e) {
		return new ResponseEntity<>(new StatusDto(403, 
						"Incorrect credentials"), 
				HttpStatus.FORBIDDEN);
	}
}
