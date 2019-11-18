package com.demo.itau.gestaoArquivos.controllers;


import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itau.demo.models.LogArquivos;
import com.demo.itau.gestaoArquivos.repositories.LogArquivosRepository;

@RestController
@RequestMapping("/logArquivos")
public class LogArquivosController {
	
	  @Autowired
	  private LogArquivosRepository repository;
	 
	  @RequestMapping(value = "/", method = RequestMethod.GET)
	  public List<LogArquivos> getAllArquivos() {
		  return repository.findAll();
	  }
	  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	  public LogArquivos getArquivoById(@PathVariable("id") ObjectId id) {
		  return repository.findBy_id(id);
	  }
	 
	  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	  public void modifyLogArquivoById(@PathVariable("id") ObjectId id, @Valid @RequestBody LogArquivos registroLog) {
		  registroLog.set_id(id);
		  repository.save(registroLog);
	  }
	 
	  @RequestMapping(value = "/", method = RequestMethod.POST)
	  public LogArquivos createArquivo(@Valid @RequestBody LogArquivos registroLog) {
		  registroLog.set_id(ObjectId.get());
		  repository.save(registroLog);
		  return registroLog;
	  }
	 
	  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	  public void deleteArquivo(@PathVariable ObjectId id) {
		  repository.delete(repository.findBy_id(id));
	  }

}
