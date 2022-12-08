package ru.dreremin.jwt.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.dreremin.jwt.security.models.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
	
	Optional<Actor> findByLogin(String login);
}
