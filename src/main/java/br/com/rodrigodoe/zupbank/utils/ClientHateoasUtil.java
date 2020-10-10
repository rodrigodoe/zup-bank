package br.com.rodrigodoe.zupbank.utils;

import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;

import br.com.rodrigodoe.zupbank.controllers.ClientController;
import br.com.rodrigodoe.zupbank.data.dtos.ClientDTO;

public class ClientHateoasUtil {
	
	
	public static void create(ClientDTO dto) {
		dto.add(ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(ClientController.class).findByid(dto.getId()))
				.withSelfRel());
	}
}
