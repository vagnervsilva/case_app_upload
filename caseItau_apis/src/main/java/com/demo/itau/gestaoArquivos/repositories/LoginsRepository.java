package com.demo.itau.gestaoArquivos.repositories;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.itau.demo.models.Logins;

public interface LoginsRepository  extends MongoRepository<Logins, String> {
	  Logins findBy_id(ObjectId _id);
	  Logins findBytoken(String token);
	  
	}
