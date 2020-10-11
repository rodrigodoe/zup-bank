package br.com.rodrigodoe.zupbank.controllers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.rodrigodoe.zupbank.data.dtos.ClientDTO;
import br.com.rodrigodoe.zupbank.services.AddressService;
import br.com.rodrigodoe.zupbank.services.ClientService;

@WebMvcTest
public class ClientControllerTests {
	
	private final String VALID_CLIENT = "{\n\"firstName\": \"Rodrigo\",\n \"birthDay\":\"2001-06-18\",\n \"lastName\": \"Carvalho\",\n	\"cpf\": \"91115434004\",\n  \"email\": \"a@a.com\"\n }";

	
	@Autowired
	private MockMvc mockMVC;


	@MockBean
	private ClientService clientService;
	@MockBean
	private AddressService adressService;

	

	public List<ClientDTO> createClientList() {
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

	public ClientDTO createClientSingleCLient() {
		ClientDTO dto = new ClientDTO();
		dto.setId(1L);
		dto.setFirstName("RODRIGO");
		dto.setLastName("CARVALHO");
		dto.setBirthDay(LocalDate.parse("2001-06-18"));
		dto.setCpf("06234132480");
		dto.setEmail("aaa@aaa.com");
		return dto;
	}

	@Test
	@DisplayName("Should create a client and return status 201")
	public void createAclientSucess() throws Exception {
		
		ClientDTO client  = createClientSingleCLient();

		when(this.clientService.create(client)).thenReturn(client);

		this.mockMVC
		.perform(post("/clients").content(VALID_CLIENT).header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.id", notNullValue()))
     	.andExpect(jsonPath("$.cpf", notNullValue()))
		.andExpect(jsonPath("$.firstName", is("RODRIGO")));
	}
	
	
	@Test
	@DisplayName("Should find a list of clients")
	public void findAllClients() throws Exception {
		
		List<ClientDTO> lista  = createClientList();

		when(this.clientService.findAll()).thenReturn(lista);
		
		this.mockMVC
		.perform(get("/clients")
		.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
	
	}
	
	

}
