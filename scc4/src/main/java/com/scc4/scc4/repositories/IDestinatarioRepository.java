package com.scc4.scc4.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scc4.scc4.models.Destinatario;

public interface IDestinatarioRepository extends JpaRepository<Destinatario, Long> {
	
	@Query("FROM Destinatario WHERE email = ?1")
	Optional<Destinatario> findByEmail(String email);
}