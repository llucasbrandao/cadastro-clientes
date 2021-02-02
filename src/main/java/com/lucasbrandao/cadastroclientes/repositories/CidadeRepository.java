package com.lucasbrandao.cadastroclientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lucasbrandao.cadastroclientes.entities.CidadeEntity;

public interface CidadeRepository extends JpaRepository<CidadeEntity, Integer>{
	
	@Query(value = "SELECT * FROM CIDADES WHERE NOME = :nome", nativeQuery = true)
	CidadeEntity findByNome(@Param("nome") String nome);
}
