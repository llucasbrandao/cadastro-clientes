package com.lucasbrandao.cadastroclientes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasbrandao.cadastroclientes.dto.EstadoDTO;
import com.lucasbrandao.cadastroclientes.services.impl.EstadoServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(path = "/v1/estados")
public class EstadoController {
	
	@Autowired
	private EstadoServiceImpl estadoService;
	
	@PostMapping(path = "/cadastrar-novo-estado")
	@ApiOperation(value = "Cadastra um novo Estado", authorizations = @Authorization(value = "Bearer"))
	public ResponseEntity<HttpStatus> cadastrarNovoEstado(@RequestBody EstadoDTO estadoDTO) {
		estadoService.cadastrarEstado(estadoDTO);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
