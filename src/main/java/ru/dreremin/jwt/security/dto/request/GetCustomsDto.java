package ru.dreremin.jwt.security.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public class GetCustomsDto extends LoginDto {

	@JsonCreator
	public GetCustomsDto(String login) { super(login); }
}
