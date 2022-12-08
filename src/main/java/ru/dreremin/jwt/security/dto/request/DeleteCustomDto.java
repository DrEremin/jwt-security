package ru.dreremin.jwt.security.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteCustomDto extends LoginDto {

	@JsonProperty(value = "number")
	private long number;
	
	@JsonCreator
	public DeleteCustomDto(String login, long number) {
		super(login);
		this.number = number;
	}
}
