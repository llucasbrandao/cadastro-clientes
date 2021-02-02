package com.lucasbrandao.cadastroclientes.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasbrandao.cadastroclientes.dto.ClienteDTO;
import com.lucasbrandao.cadastroclientes.services.impl.ClienteServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(path = "/v1/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteServiceImpl clienteServiceImpl;
	
	@PostMapping(path = "/novo-cliente", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Cadastra um novo cliente", authorizations = @Authorization(value = "Bearer"))
	public ResponseEntity<HttpStatus> novoCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
		clienteServiceImpl.novoCliente(clienteDTO);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
