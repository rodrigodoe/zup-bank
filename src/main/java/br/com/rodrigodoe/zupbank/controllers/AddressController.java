package br.com.rodrigodoe.zupbank.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodrigodoe.zupbank.data.dtos.AddressDTO;
import br.com.rodrigodoe.zupbank.data.forms.AddressForm;
import br.com.rodrigodoe.zupbank.services.AddressService;
import br.com.rodrigodoe.zupbank.utils.AddressHateoasUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/address/{clientId}")
@Api(value = "Address Endpoint", tags = { "Address" })
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping
	@ApiOperation(value = "Create a address from a client")
	public ResponseEntity<?> create(@PathVariable("clientId") Long clientId,
			@RequestBody @Valid AddressForm addressForm) {

		AddressDTO addressDto = addressService.create(clientId, addressForm);

		return ResponseEntity.created(URI.create("/address/" + addressDto.getId())).body(addressDto);

	}

	@GetMapping
	@ApiOperation(value = "Find a address from a client")
	public AddressDTO find(@PathVariable("clientId") Long clientId) {
		AddressDTO addressDto = addressService.findByClientId(clientId);
		AddressHateoasUtils.create(addressDto);
		return addressDto;
	}

	@PutMapping
	@ApiOperation(value = "Update a address from a client")
	public AddressDTO update(@PathVariable("clientId") Long clientId, @RequestBody @Valid AddressForm addressForm) {
		AddressDTO updatedAdressDto = addressService.update(clientId, addressForm);
		AddressHateoasUtils.create(updatedAdressDto);
		return updatedAdressDto;
	}

}
