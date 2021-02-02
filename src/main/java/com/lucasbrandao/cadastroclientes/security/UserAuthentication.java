package com.lucasbrandao.cadastroclientes.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuthentication implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String email;
	private String password;
	
	public UserAuthentication() {}
	
	public UserAuthentication(Integer id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public Integer getID() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		// Retorna o e-mail, ao invés do username
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
}

