package ru.dreremin.jwt.security.dto.request;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto extends LoginDto {

	@JsonProperty(value = "password")
	@NotNull
	@NotEmpty
	@Size(min = 3)
	private final String password;
	
	@JsonProperty(value = "firstName")
	@NotNull
	@NotEmpty
	@Size(min = 3)
	private final String firstName;
	
	@JsonProperty(value = "lastName")
	@NotNull
	@NotEmpty
	@Size(min = 3)
	private final String lastName;
	
	@JsonProperty(value = "birthday")
	@NotNull
	private final LocalDate birthday;
	
	@JsonCreator
	public CreateUserDto(
			String login, 
			String password, 
			String firstName, 
			String lastName,  
			String birthday) {
		super(login);
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = LocalDate.parse(
				birthday, DateTimeFormatter.ISO_LOCAL_DATE);
	}
}
