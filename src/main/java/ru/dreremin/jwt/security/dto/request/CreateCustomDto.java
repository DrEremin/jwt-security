package ru.dreremin.jwt.security.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCustomDto extends LoginDto {

	@JsonProperty(value = "name")
	@NotNull
	@Size(min = 3)
	private String name;
	
	@JsonProperty(value = "amount")
	@Min(value = 1)
	@NotNull
	private int amount;
	
	@JsonCreator
	public CreateCustomDto(String login, String name, int amount) {
		super(login);
		this.name = name;
		this.amount = amount;
	}
}
