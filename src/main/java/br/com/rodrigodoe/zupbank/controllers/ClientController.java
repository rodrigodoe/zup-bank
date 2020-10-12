package br.com.rodrigodoe.zupbank.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigodoe.zupbank.data.dtos.ClientDTO;
import br.com.rodrigodoe.zupbank.services.ClientService;
import br.com.rodrigodoe.zupbank.utils.ClientHateoasUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clients")
@Api(value = "Client Endpoint", tags = { "client" })
public class ClientController {

	@Autowired
	private ClientService clientService;

	@PostMapping
	@ApiOperation(value = "Create")
	public ResponseEntity<?> create(@RequestBody @Valid ClientDTO clientDto) {

		ClientDTO client = clientService.create(clientDto);

		ClientHateoasUtils.create(client);

		return ResponseEntity.created(URI.create("/clients/" + client.getId())).body(client);

	}

	@GetMapping
	@ApiOperation(value = "findAll")
	public List<ClientDTO> findAll() {
		List<ClientDTO> clients = this.clientService.findAll();
		clients.stream().forEach(c -> ClientHateoasUtils.create(c));
		return clients;
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "findById")
	public ClientDTO findByid(@PathVariable("id") Long id) {
		ClientDTO clientDTO = clientService.findById(id);
		ClientHateoasUtils.create(clientDTO);
		return clientDTO;
	}

	@PutMapping(value = "/{id}")
	public ClientDTO update(@PathVariable("id") Long id, @RequestBody @Valid ClientDTO clientDto) {
		ClientDTO updatedClientDto = clientService.update(id, clientDto);
		ClientHateoasUtils.create(updatedClientDto);
		return updatedClientDto;
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		clientService.delete(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/{id}/validate")
	public ClientDTO validade(@PathVariable("id") Long id) {
		ClientDTO dto = clientService.validade(id);
		ClientHateoasUtils.create(dto);
		return dto;
	}
	
	@GetMapping(value = "/{id}/confirm")
	public ClientDTO confirm(@PathVariable("id") Long id) {
		ClientDTO dto = clientService.confirm(id);
		ClientHateoasUtils.create(dto);
		return dto;
	}


}
