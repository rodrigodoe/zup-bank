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
@RequestMapping("/address")
@Api(value = "Address Endpoint", tags = { "Address" })
public class AddressController {
	

	@Autowired
	private AddressService addressService;

	@PostMapping
	@ApiOperation(value = "Create")
	public ResponseEntity<?> create(@RequestBody @Valid AddressDTO addressDTO) {

		AddressDTO addressDto = addressService.create(addressDTO);

		return ResponseEntity.created(URI.create("/client/" + addressDto.getId())).body(addressDto);

	}

	@GetMapping
	@ApiOperation(value = "findAll")
	public List<AddressDTO> findAll() {
		List<AddressDTO> addresses = addressService.findAll();
		addresses.stream().forEach(a -> AddressHateoasUtils.create(a));
		return addresses;
	} 

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "findById")
	public AddressDTO findByid(@PathVariable("id") Long id) {
		AddressDTO addressDto = addressService.findById(id);
	    AddressHateoasUtils.create(addressDto);
		return addressDto;
	} 

}
