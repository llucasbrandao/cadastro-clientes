package com.lucasbrandao.cadastroclientes.services;

import com.lucasbrandao.cadastroclientes.dto.EstadoDTO;
import com.lucasbrandao.cadastroclientes.entities.EstadoEntity;

public interface EstadoService {
	
	void cadastrarEstado(EstadoDTO estadoDTO);
	
	EstadoEntity findBySigla(String sigla);
}
