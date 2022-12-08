package ru.dreremin.jwt.security.models;

import java.time.LocalDate;

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
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "p_pk")
	private long id;
	
	@Column(name = "finstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "birthday")
	private LocalDate birthday;
	
	@Column(name = "p_afk")
	private long actorId;
	
	public Person() {}
	
	public Person(
			String firstName, 
			String lastName, 
			LocalDate birthday, 
			long actorId) {
		
		this.firstName = firstName; 
		this.lastName = lastName;
		this.birthday = birthday;
		this.actorId = actorId;
	}
}
