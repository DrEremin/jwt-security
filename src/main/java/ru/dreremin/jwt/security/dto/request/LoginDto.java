package ru.dreremin.jwt.security.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginDto implements Serializable {

	@JsonProperty(value = "login")
	@NotNull
	@NotEmpty
	@Size(min = 3)
	protected String login;
	
	@JsonCreator
	public LoginDto(String login) { this.login = login; }

	public String getLogin() { return this.login; }
	
	public void setLogin(String login) { this.login = login; }
}
