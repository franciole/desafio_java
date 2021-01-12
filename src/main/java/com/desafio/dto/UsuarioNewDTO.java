package com.desafio.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.desafio.services.validation.UserInsert;
import com.fasterxml.jackson.annotation.JsonFormat;

@UserInsert
public class UsuarioNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deverá ser entre 5 e 120 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;

	private Integer tipo;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataNascimento;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataCriacao;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataAtualizacao;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String senha;

	//private boolean enable = true;

	public UsuarioNewDTO() {
	}

	public UsuarioNewDTO(String nome, String email, Integer tipo, Date dataNascimento, Date dataCriacao,
			Date dataAtualizacao, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.tipo = tipo;
		this.dataNascimento = dataNascimento;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
		//this.enable = enable;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

//	public boolean isEnable() {
//		return enable;
//	}
//
//	public void setEnable(boolean enable) {
//		this.enable = enable;
//	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

}
