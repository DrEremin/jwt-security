package ru.dreremin.jwt.security.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class StatusDto implements Serializable {

	@JsonProperty(value = "code")
	private final int code;
	
	@JsonProperty(value = "status")
	private final String status;
}
