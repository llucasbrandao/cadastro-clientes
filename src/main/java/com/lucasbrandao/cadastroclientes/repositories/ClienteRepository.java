package com.lucasbrandao.cadastroclientes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lucasbrandao.cadastroclientes.entities.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
	
	@Query(value = "SELECT * FROM CLIENTE WHERE EMAIL = :email", nativeQuery = true)
	Optional<ClienteEntity> findByEmail(@Param("email") String email);
	
	@Query(value = "SELECT * FROM CLIENTE WHERE NOME_COMPLETO = :nomeCompleto", nativeQuery = true)
	Optional<ClienteEntity> findByNomeCompleto(@Param("nomeCompleto") String nomeCompleto);
}
