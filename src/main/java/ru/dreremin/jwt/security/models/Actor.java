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
@Table(name = "actor")
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "a_pk")
	private long id;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "passw")
	private String password;
	
	@Column(name = "authority")
	private String role;
	
	public Actor() {}
	
	public Actor(String login, String password, String role) {
		this.login = login;
		this.password = password;
		this.role = role;
	}
}
