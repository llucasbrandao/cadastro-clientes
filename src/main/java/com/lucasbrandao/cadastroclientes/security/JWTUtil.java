package com.lucasbrandao.cadastroclientes.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @author Lucas Brandão
 * Componente responsável por gerar e validar os tokens JWT
 */

@Component
public class JWTUtil {
	/**
	 * O secret e o tempo de expiração do token vêm do application.properties, injetados pelo @Value()
	 */
	
	@Value("${JWT.SECRET}")
	private String secret;
	
	@Value("${JWT.EXPIRY}")
	private Long expiration;
	
	/*
	 * Gera o token se o e-mail e a senha existirem no banco
	 */
	public String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
	
	/*
	 * Se o token tem um e-mail válido e a data de expiração é inferior a atual do sistema, o token é válido
	 */
	public boolean isValidToken(String token) {
		Claims claims = getClaims(token);
		
		return claims != null && getEmail(token) != null 
				&& claims.getExpiration() != null && new Date().before(claims.getExpiration());
			
	}
	
	// Usado para extrair o e-mail do usuário do token
	public String getEmail(String token) {
		Claims claims = getClaims(token);
		
		// Devolve o subject do token. No nosso caso, o e-mail
		return claims.getSubject();
	}

	private Claims getClaims(String token) {
		try {
			// Extrai e retorna os dados do token
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
			
		} catch (Exception e) {
			return null;
		}
	}
}
