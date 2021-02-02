package com.lucasbrandao.cadastroclientes.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lucasbrandao.cadastroclientes.entities.ClienteEntity;
import com.lucasbrandao.cadastroclientes.repositories.ClienteRepository;
import com.lucasbrandao.cadastroclientes.security.UserAuthentication;


/*
 * Classe que implementa o contrato de autenticação do usuário padrão do Spring Security.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// Usamos o email, ao invés do username
		ClienteEntity cliente = repository.findByEmail(email);
		
		if (cliente == null)
			throw new UsernameNotFoundException("User not found with email: " + email);
		
		return new UserAuthentication(cliente.getId(), cliente.getEmail(), cliente.getSenha());
	}
}