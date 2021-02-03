package com.lucasbrandao.cadastroclientes.services;

import com.lucasbrandao.cadastroclientes.entities.EstadoEntity;

public interface EstadoService {
	
	EstadoEntity findBySigla(String sigla);
}
