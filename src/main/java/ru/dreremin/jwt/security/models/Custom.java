package ru.dreremin.jwt.security.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "custom")
public class Custom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c_pk")
	private long id;
	
	@Column(name = "custom_name")
	private String name;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "c_afk")
	private long actorId;
	
	public Custom() {}
	
	public Custom(String name, int amount, long actorId) {
		this.name = name;
		this.amount = amount;
		this.actorId = actorId;
	}
}
