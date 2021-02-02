package com.lucasbrandao.cadastroclientes.services;

import com.lucasbrandao.cadastroclientes.dto.ClienteDTO;
import com.lucasbrandao.cadastroclientes.entities.ClienteEntity;

public interface ClienteServiceInterface {
	
	void novoCliente(ClienteDTO clienteDTO);
	
	ClienteEntity getAuthenticatedDetails();
	
	void isAdmin();
}
