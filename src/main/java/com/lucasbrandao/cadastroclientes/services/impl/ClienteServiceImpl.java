package com.lucasbrandao.cadastroclientes.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lucasbrandao.cadastroclientes.dto.ClienteDTO;
import com.lucasbrandao.cadastroclientes.entities.ClienteEntity;
import com.lucasbrandao.cadastroclientes.mappers.ClienteMapper;
import com.lucasbrandao.cadastroclientes.repositories.ClienteRepository;
import com.lucasbrandao.cadastroclientes.security.UserAuthentication;
import com.lucasbrandao.cadastroclientes.services.ClienteServiceInterface;

public class ClienteServiceImpl implements ClienteServiceInterface {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteMapper clienteMapper;
	
	private final BCryptPasswordEncoder B_CRIPT = new BCryptPasswordEncoder();
	
	@Override
	public void novoCliente(ClienteDTO clienteDTO) {
		this.isAdmin();
		
		try {	
			ClienteEntity clienteEntity = this.clienteMapper.fromDTO(clienteDTO);
			
			clienteEntity.setSenha(B_CRIPT.encode(clienteDTO.getSenha()));
			
			this.clienteRepository.saveAndFlush(this.clienteMapper.fromDTO(clienteDTO));
			
		} catch (Exception e) {
			throw new RuntimeException("Falha ao salvar novo cliente.");
		}
	}
	
	public static UserAuthentication getAuthenticated() {
		return (UserAuthentication) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	@Override
	public ClienteEntity getAuthenticatedDetails() {
		try {
			return this.clienteRepository.findById(getAuthenticated().getID()).get();
			
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public void isAdmin() {
		if (!this.getAuthenticatedDetails().getIsAdmin()) {
			throw new RuntimeException("Usuário sem permissão para executar a operação.");
		}
	}
}
