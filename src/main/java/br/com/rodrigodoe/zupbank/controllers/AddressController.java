package br.com.rodrigodoe.zupbank.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigodoe.zupbank.data.dtos.AddressDTO;
import br.com.rodrigodoe.zupbank.data.dtos.ClientDTO;
import br.com.rodrigodoe.zupbank.services.AddressService;
import br.com.rodrigodoe.zupbank.utils.AddressHateoasUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/client/{clientId}/address")
@Api(value = "Address Endpoint", tags = { "Address" })
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping
	@ApiOperation(value = "Create")
	public ResponseEntity<?> create(@PathVariable("clientId") Long clientId,
			@RequestBody @Valid AddressDTO addressDTO) {

		AddressDTO addressDto = addressService.create(clientId, addressDTO);

		return ResponseEntity.created(URI.create("/client/" + addressDto.getId() + "/address")).body(addressDto);

	}

	@GetMapping
	@ApiOperation(value = "findById")
	public AddressDTO find(@PathVariable("clientId") Long clientId) {
		AddressDTO addressDto = addressService.findByClientId(clientId);
		AddressHateoasUtils.create(addressDto);
		return addressDto;
	}

}