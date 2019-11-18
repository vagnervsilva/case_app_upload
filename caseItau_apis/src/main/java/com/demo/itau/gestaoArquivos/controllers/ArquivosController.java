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

import com.itau.demo.models.Arquivos;
import com.demo.itau.gestaoArquivos.repositories.ArquivosRepository;

@RestController
@RequestMapping("/arquivos")
public class ArquivosController {
	
	  @Autowired
	  private ArquivosRepository repository;
	 
	  @RequestMapping(value = "/", method = RequestMethod.GET)
	  public List<Arquivos> getAllArquivos() {
	    return repository.findAll();
	  }
	  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	  public Arquivos getArquivoById(@PathVariable("id") ObjectId id) {
	    return repository.findBy_id(id);
	  }
	 
	  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	  public void modifyArquivoById(@PathVariable("id") ObjectId id, @Valid @RequestBody Arquivos Arquivos) {
	    Arquivos.set_id(id);
	    repository.save(Arquivos);
	  }
	 
	  @RequestMapping(value = "/", method = RequestMethod.POST)
	  public Arquivos createArquivo(@Valid @RequestBody Arquivos arquivo) {
		  arquivo.set_id(ObjectId.get());
	    repository.save(arquivo);
	    return arquivo;
	  }
	 
	  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	  public void deleteArquivo(@PathVariable ObjectId id) {
	    repository.delete(repository.findBy_id(id));
	  }

}
