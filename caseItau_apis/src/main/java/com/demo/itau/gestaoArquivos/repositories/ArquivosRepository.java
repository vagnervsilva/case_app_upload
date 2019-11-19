package com.demo.itau.gestaoArquivos.repositories;



import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.itau.demo.models.Arquivos;

public interface ArquivosRepository  extends MongoRepository<Arquivos, String> {
	  Arquivos findBy_id(ObjectId _id);
		Arquivos findBynome(String nome);
	}
