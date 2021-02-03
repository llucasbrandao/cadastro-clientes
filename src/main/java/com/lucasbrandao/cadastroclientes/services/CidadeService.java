package com.lucasbrandao.cadastroclientes.services;

import java.util.List;

import com.lucasbrandao.cadastroclientes.dto.CidadeDTO;
import com.lucasbrandao.cadastroclientes.dto.CidadeDTO.CidadeInterfaceDTO;
import com.lucasbrandao.cadastroclientes.entities.CidadeEntity;

public interface CidadeService {
	
	void cadastrarCidade(CidadeDTO cidadeDTO);
	
	CidadeEntity buscarPorNome(String nome);
	
	CidadeDTO buscarPorNomeDTO(String nome);
	
	CidadeEntity buscarPorNomeAndSiglaEstado(String nome, String siglaEstado);
	
	List<CidadeInterfaceDTO> buscaCidadePeloNomeEstado(String nome, Integer limite, Integer offset);
	
	List<CidadeInterfaceDTO> buscaCidadePelaSiglaEstado(String sigla, Integer limite, Integer offset);
}
