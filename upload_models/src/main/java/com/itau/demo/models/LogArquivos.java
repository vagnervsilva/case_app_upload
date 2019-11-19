package com.itau.demo.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class LogArquivos {
	
	@Id
	public ObjectId _id;

	private String arquivo;
	private String acao;
	private String autor;
	private String dataHora;

	public LogArquivos() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public LogArquivos(ObjectId _id, String arquivo, String acao, String autor, String dataHora) {
		super();
		this._id = _id;
		this.arquivo = arquivo;
		this.acao = acao;
		this.autor = autor;
		this.dataHora = dataHora;
	}



	// ObjectId needs to be converted to string
	public String get_id() { return _id.toHexString(); }
	public void set_id(ObjectId _id) { this._id = _id; }

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	
	
	public String getAutor() {
		return autor;
	}



	public void setAutor(String autor) {
		this.autor = autor;
	}



	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	

	
}
