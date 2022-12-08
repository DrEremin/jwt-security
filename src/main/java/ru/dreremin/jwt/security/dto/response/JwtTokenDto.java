package ru.dreremin.jwt.security.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtTokenDto implements Serializable {
	
	@JsonProperty(value = "jwtToken")
	private String jwtToken;
	
	@JsonCreator
	public JwtTokenDto(String jwtToken) { this.jwtToken = jwtToken; }
}
