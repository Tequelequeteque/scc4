package com.scc4.scc4.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scc4.scc4.models.Remetente;

public interface IRemetenteRepository extends JpaRepository<Remetente, Long> {
	
	@Query("FROM Remetente WHERE email = ?1")
	Optional<Remetente> findByEmail(String email);
}