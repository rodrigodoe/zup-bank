package br.com.rodrigodoe.zupbank.models.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.Before;

import br.com.rodrigodoe.zupbank.builder.ClientBuilder;
import br.com.rodrigodoe.zupbank.data.dtos.ClientDTO;
import br.com.rodrigodoe.zupbank.data.models.Client;
import br.com.rodrigodoe.zupbank.utils.ClassConverterBuilder;

public class ClientConverterTests {

	@Test
	@DisplayName("Should convert a Client to ClientDTO")
	public void convertClient2ClientDTO() {
		Client client = ClientBuilder.buildClient();
		ClientDTO DTO = ClassConverterBuilder.build(client, ClientDTO.class);
		Assert.assertEquals(client.getId(), DTO.getId());
	}

}
