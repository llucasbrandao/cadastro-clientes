package com.lucasbrandao.cadastroclientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lucasbrandao.cadastroclientes.entities.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
	
	@Query(value = "SELECT * FROM CLIENTE WHERE EMAIL = :email", nativeQuery = true)
	ClienteEntity findByEmail(@Param("email") String email);
}
