package com.demo.itau.gestaoArquivos.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.itau.demo.models.Acessos;

public interface AcessosRepository  extends MongoRepository<Acessos, String> {
	  Acessos findBy_id(ObjectId _id);
	}
