package com.itau.demo.models;

import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

@EntityScan
public class Acessos {

	@Id
	public ObjectId _id;

	private String nome;
	private String descricao;
	private String uploader;
	private String dataHora;
	private String ativo;

	  // Constructors
	public Acessos() {}
	
	public Acessos(ObjectId _id, String nome, String descricao, String uploader, String dataHora, String ativo) {
		this._id = _id;
		this.nome = nome;
		this.descricao = descricao;
		this.uploader = uploader;
		this.dataHora = dataHora;
		this.ativo = ativo;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public String getDataHora() {
		return dataHora;
	}
	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	
}
