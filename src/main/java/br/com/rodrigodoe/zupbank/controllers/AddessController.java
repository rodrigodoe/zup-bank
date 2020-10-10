package br.com.rodrigodoe.zupbank.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigodoe.zupbank.data.dtos.AddressDTO;
import br.com.rodrigodoe.zupbank.services.AddressService;
import br.com.rodrigodoe.zupbank.services.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/client/{clientId}/address")
@Api(value = "Address Endpoint", tags = { "Address" })
public class AddessController {
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private ClientService clientService;

	@PostMapping
	@ApiOperation(value = "Create")
	public ResponseEntity<?> create(@PathVariable("clientId") Long id, @RequestBody @Valid AddressDTO addressDto) {
		var client = clientService.findById(id);
		var address = addressService.create(client, addressDto);
		return ResponseEntity.created(URI.create("/client/"+id+"/address/"+address.getId())).body(address);

	}

}
