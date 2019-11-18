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

import com.itau.demo.models.Logins;
import com.demo.itau.gestaoArquivos.repositories.LoginsRepository;

@RestController
@RequestMapping("/logins")
public class LoginsController {
	
	  @Autowired
	  private LoginsRepository repository;
	 
	  @RequestMapping(value = "/", method = RequestMethod.GET)
	  public List<Logins> getAllLogins() {
	    return repository.findAll();
	  }
	  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	  public Logins getLoginById(@PathVariable("id") ObjectId id) {
	    return repository.findBy_id(id);
	  }
	 
	  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	  public void modifyLoginById(@PathVariable("id") ObjectId id, @Valid @RequestBody Logins Logins) {
	    Logins.set_id(id);
	    repository.save(Logins);
	  }
	 
	  @RequestMapping(value = "/", method = RequestMethod.POST)
	  public Logins createLogin(@Valid @RequestBody Logins Logins) {
	    Logins.set_id(ObjectId.get());
	    repository.save(Logins);
	    return Logins;
	  }
	 
	  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	  public void deleteLogin(@PathVariable ObjectId id) {
	    repository.delete(repository.findBy_id(id));
	  }

}
