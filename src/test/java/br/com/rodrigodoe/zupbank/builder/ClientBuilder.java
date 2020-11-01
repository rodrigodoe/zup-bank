package br.com.rodrigodoe.zupbank.builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.rodrigodoe.zupbank.data.dtos.ClientDTO;
import br.com.rodrigodoe.zupbank.data.forms.ClientForm;
import br.com.rodrigodoe.zupbank.data.models.Client;

public class ClientBuilder {

	public static final String VALID_CLIENT_REQUEST = "{\n\"firstName\": \"Rodrigo\",\n \"birthDay\":\"2001-06-18\",\n \"lastName\": \"Carvalho\",\n	\"cpf\": \"91115434004\",\n  \"email\": \"aaa@aaa.com\"\n }";

	public static List<ClientDTO> buildList() {
		List<ClientDTO> lista = new ArrayList<>();
		ClientDTO dto = new ClientDTO();
		dto.setId(1L);
		dto.setFirstName("RODRIGO");
		dto.setLastName("CARVALHO");
		dto.setBirthDay(LocalDate.parse("2001-06-18"));
		dto.setCpf("53009314035");
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
		client.setCpf("53009314035");
		client.setEmail("aaa@aaa.com");
		return client;
	}

	public static ClientDTO buildClientDTO() {
		ClientDTO dto = new ClientDTO();
		dto.setId(1L);
		dto.setFirstName("RODRIGO");
		dto.setLastName("CARVALHO");
		dto.setBirthDay(LocalDate.parse("2001-06-18"));
		dto.setCpf("53009314035");
		dto.setEmail("aaa@aaa.com");
		return dto;
	}

	public static ClientForm buildClientForm() {
		ClientForm form = new ClientForm();
		form.setFirstName("RODRIGO");
		form.setLastName("CARVALHO");
		form.setBirthDay(LocalDate.parse("2001-06-18"));
		form.setCpf("53009314035");
		form.setEmail("aaa@aaa.com");
		return form;
	}

}
