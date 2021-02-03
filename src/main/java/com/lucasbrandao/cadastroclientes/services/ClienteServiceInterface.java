package com.lucasbrandao.cadastroclientes.services;

import com.lucasbrandao.cadastroclientes.dto.ClienteDTO;
import com.lucasbrandao.cadastroclientes.entities.ClienteEntity;

public interface ClienteServiceInterface {
	
	void novoCliente(ClienteDTO clienteDTO);
	
	void editarCliente(ClienteDTO clienteDTO);
	
	void removerCliente(Integer id);
	
	ClienteDTO buscarClientePorID(Integer id);
	
	ClienteDTO buscarClientePorEmail(String email);
	
	ClienteDTO buscarClientePorNome(String nome);
	
	ClienteEntity getAuthenticatedDetails();
	
	void isAdmin();
}
