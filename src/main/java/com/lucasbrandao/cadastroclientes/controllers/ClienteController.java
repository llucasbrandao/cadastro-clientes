package com.lucasbrandao.cadastroclientes.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	private ClienteServiceImpl clienteService;
	
	@PostMapping(path = "/novo-cliente", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Cadastra um novo cliente", authorizations = @Authorization(value = "Bearer")) 
	public ResponseEntity<HttpStatus> novoCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
		clienteService.novoCliente(clienteDTO);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PatchMapping(path = "/editar-cliente")
	@ApiOperation(value = "Editar um cliente", authorizations = @Authorization(value = "Bearer"))
	public ResponseEntity<HttpStatus> editarCliente(@RequestBody ClienteDTO clienteDTO) {
		clienteService.editarCliente(clienteDTO);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/remover-cliente-por-id/{id}")
	@ApiOperation(value = "Remover cliente por ID", authorizations = @Authorization(value = "Bearer"))
	public ResponseEntity<HttpStatus> removerClientePorID(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(HttpStatus.OK);	
	}
	
	@GetMapping(path = "/buscar-cliente-por-id/{id}")
	@ApiOperation(value = "Busca um cliente por ID", authorizations = @Authorization(value = "Bearer"))
	public ResponseEntity<ClienteDTO> buscarClientePorID(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(clienteService.buscarClientePorID(id));
	}
	
	@GetMapping(path = "/buscar-cliente-por-email/{email}")
	@ApiOperation(value = "Busca um cliente por ID", authorizations = @Authorization(value = "Bearer"))
	public ResponseEntity<ClienteDTO> buscarClientePorEmail(@PathVariable("email") String email) {
		return ResponseEntity.ok(clienteService.buscarClientePorEmail(email));
	}
	
	@GetMapping(path = "/buscar-cliente-por-nome/{nome}")
	@ApiOperation(value = "Busca um cliente pelo nome", authorizations = @Authorization(value = "Bearer"))
	public ResponseEntity<ClienteDTO> buscarClientePorNome(@PathVariable("nome") String nome) {
		return ResponseEntity.ok(clienteService.buscarClientePorNome(nome));
	}
}
