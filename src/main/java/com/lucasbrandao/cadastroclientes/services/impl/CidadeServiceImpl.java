package com.lucasbrandao.cadastroclientes.services.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasbrandao.cadastroclientes.dto.CidadeDTO;
import com.lucasbrandao.cadastroclientes.entities.CidadeEntity;
import com.lucasbrandao.cadastroclientes.mappers.CidadeMapper;
import com.lucasbrandao.cadastroclientes.repositories.CidadeRepository;
import com.lucasbrandao.cadastroclientes.services.CidadeService;

@Service
public class CidadeServiceImpl implements CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteServiceImpl clienteService;
	
	@Autowired
	private EstadoServiceImpl estadoServiceImpl;
	
	@Autowired
	private CidadeMapper cidadeMapper;
	
	@Override
	public void cadastrarCidade(CidadeDTO cidadeDTO) {
		this.clienteService.isAdmin();
		
		CidadeEntity cidadeEntity = cidadeMapper.fromDTO(cidadeDTO);
		
		cidadeEntity.setEstado(estadoServiceImpl.findBySigla(cidadeDTO.getEstado().getSigla()));
		
		this.cidadeRepository.saveAndFlush(cidadeEntity);
	}
	
	@Override
	public CidadeEntity buscarPorNome(String nome) throws NoSuchElementException {
		Optional<CidadeEntity> cidadeEntity = cidadeRepository.findByNome(nome);
			
		if (!cidadeEntity.isPresent()) {
			throw new NoSuchElementException("A cidade " + nome + " não existe.");
			
		}
		
		return cidadeEntity.get();
	}
	
	@Override
	public CidadeEntity buscarPorNomeAndSiglaEstado(String nome, String siglaEstado) {
		Optional<CidadeEntity> cidadeEntity = cidadeRepository.findByNomeAndSiglaEstado(nome, siglaEstado);
		
		if (!cidadeEntity.isPresent()) {
			throw new NoSuchElementException("A cidade '" + nome + "' não existe.");
			
		}
		
		return cidadeEntity.get();
		
	}
}
