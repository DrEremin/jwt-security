package ru.dreremin.jwt.security.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public class DeleteUserDto extends LoginDto {
	
	@JsonCreator
	public DeleteUserDto(String login) { super(login); }
}
