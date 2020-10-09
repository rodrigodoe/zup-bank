package br.com.rodrigodoe.zupbank.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import br.com.rodrigodoe.zupbank.data.dto.ClientDTO;
import br.com.rodrigodoe.zupbank.data.forms.ClientForm;
import br.com.rodrigodoe.zupbank.data.model.Client;
import br.com.rodrigodoe.zupbank.services.ClientService;
import javassist.tools.web.BadHttpRequest;



@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid ClientForm clientForm) {
		
	    	 ClientDTO client = clientService.create(clientForm);
	  
	   
		
		return ResponseEntity.created(URI.create("/clients/"+client.getId())).body(client);
		
		
	}
	

}
