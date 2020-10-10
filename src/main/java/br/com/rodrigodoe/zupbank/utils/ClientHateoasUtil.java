package br.com.rodrigodoe.zupbank.utils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import br.com.rodrigodoe.zupbank.controllers.ClientController;
import br.com.rodrigodoe.zupbank.data.dtos.ClientDTO;

public class ClientHateoasUtil {
	
	
	public static void create(ClientDTO model) {
		model.add(linkTo(methodOn(ClientController.class).findByid(model.getId()))
				.withSelfRel());
	}
}
