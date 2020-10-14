package br.com.rodrigodoe.zupbank.builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.rodrigodoe.zupbank.data.dtos.ClientDTO;
import br.com.rodrigodoe.zupbank.data.models.Client;

public class ClientBuilder {
	public static List<ClientDTO> buildList() {
		List<ClientDTO> lista = new ArrayList<>();
		ClientDTO dto = new ClientDTO();
		dto.setId(1L);
		dto.setFirstName("RODRIGO");
		dto.setLastName("CARVALHO");
		dto.setBirthDay(LocalDate.parse("2001-06-18"));
		dto.setCpf("06234132480");
		dto.setEmail("aaa@aaa.com");
		lista.add(dto);
		return lista;
	}

	public static Client buildClient() {
		Client client = new Client();
		client.setId(3L);
		client.setFirstName("RODRIGO");
		client.setLastName("CARVALHO");
		client.setBirthDay(LocalDate.parse("2001-06-18"));
		client.setCpf("06234132480");
		client.setEmail("aaa@aaa.com");
		return client;
	}

}
