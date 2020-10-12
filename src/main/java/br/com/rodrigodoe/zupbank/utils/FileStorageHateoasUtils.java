package br.com.rodrigodoe.zupbank.utils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;

import br.com.rodrigodoe.zupbank.controllers.AddressController;
import br.com.rodrigodoe.zupbank.controllers.BucketController;
import br.com.rodrigodoe.zupbank.controllers.ClientController;
import br.com.rodrigodoe.zupbank.data.dtos.FileStorageDTO;

public class FileStorageHateoasUtils {

	public static void create(FileStorageDTO model) {
		model.add(linkTo(methodOn(BucketController.class).find(model.getId())).withSelfRel())
				.add(linkTo(methodOn(BucketController.class, model.getClient().getId())
						.downloadFile(model.getClient().getId(), model.getFilename())).withRel("download"))
				.add(linkTo(methodOn(ClientController.class).findByid(model.getClient().getId())).withRel("client"));
	}
}
