package com.demo.itau.gestaoArquivos.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.itau.demo.models.LogArquivos;;

public interface LogArquivosRepository  extends MongoRepository<LogArquivos, String> {
	LogArquivos findBy_id(ObjectId _id);
	List<LogArquivos> findByarquivo(String arquivo);
	}
