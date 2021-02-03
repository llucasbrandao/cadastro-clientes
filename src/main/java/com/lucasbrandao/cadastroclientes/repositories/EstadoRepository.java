package com.lucasbrandao.cadastroclientes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lucasbrandao.cadastroclientes.entities.EstadoEntity;

public interface EstadoRepository extends JpaRepository<EstadoEntity, Integer>{
	
	@Query(value = "SELECT * FROM ESTADOS LIMIT 1", nativeQuery = true)
	Optional<EstadoEntity> findOne();
	
	@Query(value = "SELECT * FROM ESTADOS WHERE SIGLA = :sigla", nativeQuery = true)
	Optional<EstadoEntity> findBySiglaEstado(@Param("sigla") String sigla);
}
