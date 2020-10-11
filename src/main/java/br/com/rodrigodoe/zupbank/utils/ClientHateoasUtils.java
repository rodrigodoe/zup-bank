package br.com.rodrigodoe.zupbank.utils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.rodrigodoe.zupbank.controllers.AddressController;
import br.com.rodrigodoe.zupbank.controllers.ClientController;
import br.com.rodrigodoe.zupbank.data.dtos.ClientDTO;

public class ClientHateoasUtils {
	
	
	public static void create(ClientDTO model) {
		model.add(linkTo(methodOn(ClientController.class).findByid(model.getId()))
				.withSelfRel());
		
		model.add(linkTo(methodOn(AddressController.class).find(model.getId()))
				.withRel("Address"));
	}
}
	