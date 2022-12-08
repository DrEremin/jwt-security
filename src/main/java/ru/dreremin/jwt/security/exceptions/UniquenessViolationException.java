package ru.dreremin.jwt.security.exceptions;

public class UniquenessViolationException extends RuntimeException {
	
	public UniquenessViolationException(String message) {
		super(message);
	}
	
	@Override
	public String toString() {
		return "UniquenessViolationException: " + super.getMessage();
	}
}
