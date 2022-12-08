package ru.dreremin.jwt.security.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.dreremin.jwt.security.models.Custom;

@Repository
public interface CustomRepository extends JpaRepository<Custom, Long>{
	
	List<Custom> findByActorId(long actorId);
}
