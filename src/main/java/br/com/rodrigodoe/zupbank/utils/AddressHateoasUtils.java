package br.com.rodrigodoe.zupbank.utils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.rodrigodoe.zupbank.controllers.AddressController;
import br.com.rodrigodoe.zupbank.data.dtos.AddressDTO;

public class AddressHateoasUtils {
	
	public static void create(AddressDTO model) {
		model.add(linkTo(methodOn(AddressController.class).findByid(model.getId()))
				.withSelfRel());
	}
}
