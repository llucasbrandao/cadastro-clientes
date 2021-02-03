package com.lucasbrandao.cadastroclientes.services.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasbrandao.cadastroclientes.entities.EstadoEntity;
import com.lucasbrandao.cadastroclientes.repositories.EstadoRepository;
import com.lucasbrandao.cadastroclientes.services.EstadoService;

@Service
public class EstadoServiceImpl implements EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Override
	public EstadoEntity findBySigla(String sigla) {
		return estadoRepository.findBySiglaEstado(sigla).orElseGet(() -> {
			throw new NoSuchElementException("Nenhum Estado foi encontrado com a sigla '" + sigla + "'.");
		});
	}
}
