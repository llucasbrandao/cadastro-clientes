package com.lucasbrandao.cadastroclientes.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasbrandao.cadastroclientes.dto.CredentialsDTO;

public class JWTAuthFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	private JWTUtil jwtUtil;
	
	public JWTAuthFilter(AuthenticationManager authManager, JWTUtil jwtUtil) {
		this.authenticationManager = authManager;
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
		try {
			// Tentamos pegar os dados de login da requisição
			CredentialsDTO credentialsDto = new ObjectMapper()
					.readValue(req.getInputStream(), CredentialsDTO.class);
			
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
					new UsernamePasswordAuthenticationToken(credentialsDto.getEmail(), credentialsDto.getPassword(), new ArrayList<>());
			
			Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			
			return authentication;
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) 
			throws IOException, ServletException {
		
		// Se a autenticação no attemptAuthentication passar, geramos o token e o adicionamos à requisição
		res.addHeader("Authorization", "Bearer " + jwtUtil.generateToken(((UserAuthentication) auth.getPrincipal()).getUsername()));
	}
}