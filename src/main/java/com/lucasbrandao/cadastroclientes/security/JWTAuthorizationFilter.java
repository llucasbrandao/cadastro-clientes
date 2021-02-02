package com.lucasbrandao.cadastroclientes.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private JWTUtil jwtUtil;
	private UserDetailsService userDetailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super(authenticationManager);
		
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// Intercepta o header que contém o token
		String header = request.getHeader("Authorization");
		
		if (header != null && header.startsWith("Bearer ")) {
			// Faz a autenticação interna com o Spring Security
			UsernamePasswordAuthenticationToken auth = getAuthentication(request, header.substring(7));
			
			// Se o auth não for null, significa que o token do usuário é válido
			if (auth != null)
				SecurityContextHolder.getContext().setAuthentication(auth);
		}	
		
		// Prossegue com a request, mesmo que o auth seja inválido
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, String token) {
		if (jwtUtil.isValidToken(token)) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtil.getEmail(token));
			
			return new UsernamePasswordAuthenticationToken(userDetails, null, new ArrayList<>());
		}
		
		return null;
	}
}