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

import com.itau.demo.models.Acessos;
import com.demo.itau.gestaoArquivos.repositories.AcessosRepository;

@RestController
@RequestMapping("/acessos")
public class AcessosController {
	
	  @Autowired
	  private AcessosRepository repository;
	 
	  @RequestMapping(value = "/", method = RequestMethod.GET)
	  public List<Acessos> getAllAcessos() {
	    return repository.findAll();
	  }
	  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	  public Acessos getAcessoById(@PathVariable("id") ObjectId id) {
	    return repository.findBy_id(id);
	  }
	 
	  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	  public void modifyAcessoById(@PathVariable("id") ObjectId id, @Valid @RequestBody Acessos Acessos) {
	    Acessos.set_id(id);
	    repository.save(Acessos);
	  }
	 
	  @RequestMapping(value = "/", method = RequestMethod.POST)
	  public Acessos createAcesso(@Valid @RequestBody Acessos Acessos) {
	    Acessos.set_id(ObjectId.get());
	    repository.save(Acessos);
	    return Acessos;
	  }
	 
	  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	  public void deleteAcesso(@PathVariable ObjectId id) {
	    repository.delete(repository.findBy_id(id));
	  }

}
