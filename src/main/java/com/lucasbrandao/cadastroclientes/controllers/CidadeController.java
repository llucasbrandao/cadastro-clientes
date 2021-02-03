package com.lucasbrandao.cadastroclientes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasbrandao.cadastroclientes.dto.CidadeDTO;
import com.lucasbrandao.cadastroclientes.services.impl.CidadeServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(path = "/v1/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeServiceImpl cidadeService;
	
	@ApiOperation(value = "Cadastra uma nova cidade", authorizations = @Authorization(value = "Bearer")) 
	@PostMapping(path = "/cadastrar-nova-cidade")
	public ResponseEntity<HttpStatus> cadastrarCidade(@RequestBody CidadeDTO cidadeDTO) {
		cidadeService.cadastrarCidade(cidadeDTO);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
