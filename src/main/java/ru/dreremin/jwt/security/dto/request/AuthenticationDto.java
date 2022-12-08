package ru.dreremin.jwt.security.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class AuthenticationDto extends LoginDto {

	@JsonProperty(value = "password")
	@Size(min = 3)
	@NotNull
	@NotEmpty
	private String password;
	
	@JsonCreator
	public AuthenticationDto(String login, String password) {
		super(login);
		this.password = password;
	}
}
