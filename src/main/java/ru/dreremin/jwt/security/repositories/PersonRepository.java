package ru.dreremin.jwt.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.dreremin.jwt.security.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
