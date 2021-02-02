package com.lucasbrandao.cadastroclientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasbrandao.cadastroclientes.entities.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

}
