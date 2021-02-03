package com.lucasbrandao.cadastroclientes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lucasbrandao.cadastroclientes.entities.CidadeEntity;

public interface CidadeRepository extends JpaRepository<CidadeEntity, Integer>{
	
	@Query(value = "SELECT * FROM CIDADES WHERE NOME = :nome", nativeQuery = true)
	Optional<CidadeEntity> findByNome(@Param("nome") String nome);
	
	@Query(value = "SELECT c.ID, c.NOME FROM CIDADES c"
			+ " INNER JOIN ESTADOS e"
			+ " ON e.ID = c.ESTADO_ID"
			+ " WHERE c.NOME = :nome AND e.SIGLA = :sigla", nativeQuery = true)
	Optional<CidadeEntity> findByNomeAndSiglaEstado(@Param("nome") String nome, @Param("sigla") String sigla);
}
