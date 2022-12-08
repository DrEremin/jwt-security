package ru.dreremin.jwt.security.dto.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomDto implements Serializable {

	@JsonProperty(value = "customId")
	private long id;
	
	@JsonProperty(value = "customName")
	private String name;
	
	@JsonProperty(value = "amount")
	private int amount;
	
	@JsonProperty(value = "userId")
	private long actorId;
	
	public CustomDto(long id, String name, int amount, long actorId) {
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.actorId = actorId;
	}
}
