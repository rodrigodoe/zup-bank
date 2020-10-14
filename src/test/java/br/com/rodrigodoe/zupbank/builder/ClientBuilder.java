package br.com.rodrigodoe.zupbank.builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.rodrigodoe.zupbank.data.dtos.ClientDTO;

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

	public static ClientDTO build() {
		ClientDTO dto = new ClientDTO();
		dto.setId(3L);
		dto.setFirstName("RODRIGO");
		dto.setLastName("CARVALHO");
		dto.setBirthDay(LocalDate.parse("2001-06-18"));
		dto.setCpf("06234132480");
		dto.setEmail("aaa@aaa.com");
		return dto;
	}

}
