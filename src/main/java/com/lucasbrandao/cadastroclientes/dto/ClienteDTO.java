package com.lucasbrandao.cadastroclientes.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasbrandao.cadastroclientes.enums.SexoEnum;

public class ClienteDTO {
	
	@JsonIgnore
	private Integer id;
	
	private String nomeCompleto;
	
	private String email;
	
	private SexoEnum sexo; 
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;
	
	private String cidade;
	
	private String estado;
	
	private Boolean isAdmin;
	
	private String senha;
	
	public ClienteDTO() {}

	public ClienteDTO(Integer id, String nomeCompleto, String email, SexoEnum sexo, Date dataNascimento, String cidade,
			String estado, Boolean isAdmin, String senha) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.cidade = cidade;
		this.estado = estado;
		this.isAdmin = isAdmin;
		this.senha = senha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
