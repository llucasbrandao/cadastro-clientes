package com.lucasbrandao.cadastroclientes.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.lucasbrandao.cadastroclientes.enums.SexoEnum;
import com.lucasbrandao.cadastroclientes.utils.DateConverter;

@Entity
@Table(name = "CLIENTE")
public class ClienteEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "NOME_COMPLETO")
	private String nomeCompleto;
	
	@Column()
	private String email;
	
	@Column()
	@Enumerated(EnumType.STRING)
	private SexoEnum sexo; 
	
	@Column(name = "DATA_NASCIMENTO")
	private Date dataNascimento;
	
	@OneToOne()
	@JoinColumn(name = "CIDADE_ID")
	private CidadeEntity cidade;
	
	@Column(name = "IS_ADMIN")
	private Boolean isAdmin;
	
	@Column()
	private String senha;
	
	@Transient
	private Integer idade;

	public ClienteEntity() {}
	
	public ClienteEntity(Integer id, String nomeCompleto, String email, SexoEnum sexo, Date dataNascimento,
			CidadeEntity cidade, Boolean isAdmin, String senha) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.cidade = cidade;
		this.isAdmin = isAdmin;
		this.senha = senha;
	}
	
	public ClienteEntity(Integer id, String nomeCompleto, String email, SexoEnum sexo, Date dataNascimento,
			CidadeEntity cidade, Boolean isAdmin, String senha, Integer idade) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.cidade = cidade;
		this.isAdmin = isAdmin;
		this.senha = senha;
		this.idade = idade;
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

	public CidadeEntity getCidade() {
		return cidade;
	}

	public void setCidade(CidadeEntity cidade) {
		this.cidade = cidade;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdade() {
		if (this.idade == null && this.dataNascimento != null) {
			this.setIdade(DateConverter.getYearsDifferenceFromNow(DateConverter.dateToLocalDateConverter(this.dataNascimento)));
		}
		
		return this.idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}
}
