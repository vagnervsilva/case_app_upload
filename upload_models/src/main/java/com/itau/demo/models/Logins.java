package com.itau.demo.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Logins {


	@Id
	public ObjectId _id;

	private String nome;
	private String email;
	private String token;
	private String dataHora;
	
	  // Constructors
	public Logins() {}
	
	public Logins(ObjectId _id, String nome, String email, String token, String dataHora) {
		this._id = _id;
		this.nome = nome;
		this.token = token;
		this.email = email;
		this.dataHora = dataHora;

	}

	public Logins(String nome, String email, String token, String dataHora) {
		this.nome = nome;
		this.token = token;
		this.email = email;
		this.dataHora = dataHora;

	}
	  
	// ObjectId needs to be converted to string
	public String get_id() { return _id.toHexString(); }
	public void set_id(ObjectId _id) { this._id = _id; }

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}




}
