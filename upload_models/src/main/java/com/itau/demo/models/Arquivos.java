package com.itau.demo.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Arquivos {
	@Id
	public ObjectId _id;

	private String nome;
	private String descricao;
	private String autorUpload;
	private String dataHora;
	
	
	public Arquivos() {
	}


	public Arquivos(ObjectId _id, String nome, String descricao, String autorUpload, String dataHora) {
		super();
		this._id = _id;
		this.nome = nome;
		this.descricao = descricao;
		this.autorUpload = autorUpload;
		this.dataHora = dataHora;
	}

	

	public ObjectId get_id() {
		return _id;
	}


	public void set_id(ObjectId _id) {
		this._id = _id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getAutorUpload() {
		return autorUpload;
	}


	public void setAutorUpload(String autorUpload) {
		this.autorUpload = autorUpload;
	}


	public String getDataHora() {
		return dataHora;
	}


	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	
	
	
	

}
