package com.lucasbrandao.cadastroclientes.services;

import com.lucasbrandao.cadastroclientes.dto.CidadeDTO;
import com.lucasbrandao.cadastroclientes.entities.CidadeEntity;

public interface CidadeService {
	
	void cadastrarCidade(CidadeDTO cidadeDTO);
	
	CidadeEntity buscarPorNome(String nome);
	
	CidadeEntity buscarPorNomeAndSiglaEstado(String nome, String siglaEstado);
}
