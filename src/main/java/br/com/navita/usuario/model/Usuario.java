package br.com.navita.usuario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_usuario")
	private Long id;

	@Column(name = "nm_usuario", nullable = false, length = 150)
	private String nome;

	@Column(name = "email", nullable = false, unique = true, length = 200)
	private String email;

	@Column(name = "senha", nullable = false)
	private String senha;

	@Column(name = "papel", length = 100)
	private String papeis;

	public Usuario() {}

	public Usuario(String nome, String email, String senha, String papeis) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.papeis = papeis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPapeis() {
		return papeis;
	}

	public void setPapeis(String papeis) {
		this.papeis = papeis;
	}

}
