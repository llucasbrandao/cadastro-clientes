package com.lucasbrandao.cadastroclientes.services.impl;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucasbrandao.cadastroclientes.dto.ClienteDTO;
import com.lucasbrandao.cadastroclientes.entities.ClienteEntity;
import com.lucasbrandao.cadastroclientes.exceptions.UserPermissionsException;
import com.lucasbrandao.cadastroclientes.mappers.ClienteMapper;
import com.lucasbrandao.cadastroclientes.repositories.ClienteRepository;
import com.lucasbrandao.cadastroclientes.security.UserAuthentication;
import com.lucasbrandao.cadastroclientes.services.ClienteServiceInterface;

@Service
public class ClienteServiceImpl implements ClienteServiceInterface {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CidadeServiceImpl cidadeService;
	
	@Autowired
	private ClienteMapper clienteMapper;
	
	private final BCryptPasswordEncoder B_CRIPT = new BCryptPasswordEncoder();
	
	@Override
	public void novoCliente(ClienteDTO clienteDTO) {
		this.isAdmin();
		
		try {
			if (clienteRepository.findByEmail(clienteDTO.getEmail()).isPresent()) {
				throw new RuntimeException("Já existe um usuário com o mesmo e-mail cadastrado.");
			}
			
			ClienteEntity clienteEntity = this.clienteMapper.fromDTO(clienteDTO);
			
			clienteEntity.setCidade(cidadeService.buscarPorNomeAndSiglaEstado(clienteDTO.getCidade().getNome(), clienteDTO.getCidade().getEstado().getSigla()));
			clienteEntity.setSenha(B_CRIPT.encode(clienteDTO.getSenha()));
			
			this.clienteRepository.saveAndFlush(clienteEntity);
			
		} catch (Exception e) {
			throw new RuntimeException("Falha ao salvar novo cliente: " + e.getMessage());
		}
	}
	
	@Transactional
	@Override
	public void editarCliente(ClienteDTO clienteDTO) {
		if (clienteDTO.getId() == null) {
			clienteDTO.setId(getAuthenticated().getID());
		}
				
		if (clienteDTO.getId() != getAuthenticated().getID() 
				|| clienteDTO.getIsAdmin() != null) {
			
			this.isAdmin();
		}
		
		try {
			ClienteEntity clienteEntity = clienteMapper.fromDTO(clienteDTO);
			
			if (clienteDTO.getCidade() != null && clienteDTO.getCidade().getEstado() != null) {
				clienteEntity.setCidade(cidadeService.buscarPorNomeAndSiglaEstado(clienteDTO.getCidade().getNome(), clienteDTO.getCidade().getEstado().getSigla()));
			}
			
			if (clienteDTO.getSenha() != null) {
				clienteEntity.setSenha(B_CRIPT.encode(clienteDTO.getSenha()));
			}
			
			this.clienteRepository.save(clienteEntity);
			
		} catch (Exception e) {
			throw new RuntimeException("Falha ao atualizar o cliente: " + e.getMessage());
		}
	}
	
	@Override
	public void removerCliente(Integer id) {
		if (id != getAuthenticated().getID()) {
			this.isAdmin();
		}
		
		this.clienteRepository.deleteById(id);
	}
	
	@Override
	public ClienteDTO buscarClientePorID(Integer id) {
		if (id != getAuthenticated().getID()) {
			this.isAdmin();
		}
		
		return clienteMapper.toDTO(clienteRepository.findById(id).orElseGet(() -> {
			throw new NoSuchElementException("Nenhum usuário foi encontrado com o ID " + id + ".");
		}));
	}
	
	@Override
	public ClienteDTO buscarClientePorEmail(String email) {
		if (!email.equals(getAuthenticated().getUsername())) {
			this.isAdmin();
		}
		
		return clienteMapper.toDTO(clienteRepository.findByEmail(email).orElseGet(() -> {
			throw new NoSuchElementException("Nenhum usuário foi encontrado com o email: " + email + ".");
		}));
	}
	
	@Override
	public ClienteDTO buscarClientePorNome(String nome) {
		if (!nome.equals(getAuthenticated().getNome())) {
			this.isAdmin();
		}
		
		return clienteMapper.toDTO(clienteRepository.findByNomeCompleto(nome).orElseGet(() -> {
			throw new NoSuchElementException("Nenhum usuário foi encontrado com o nome: " + nome + ".");
		}));
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
			throw new UserPermissionsException("Usuário sem permissão para executar a operação.");
		}
	}
}
