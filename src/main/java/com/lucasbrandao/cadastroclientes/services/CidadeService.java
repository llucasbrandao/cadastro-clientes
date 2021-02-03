package com.lucasbrandao.cadastroclientes.services;

import com.lucasbrandao.cadastroclientes.entities.CidadeEntity;

public interface CidadeService {
	
	CidadeEntity buscarPorNome(String nome);
	
	CidadeEntity buscarPorNomeAndSiglaEstado(String nome, String siglaEstado);
}
