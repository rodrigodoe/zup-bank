package br.com.rodrigodoe.zupbank.controllers;

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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.rodrigodoe.zupbank.builder.ClientBuilder;
import br.com.rodrigodoe.zupbank.data.dtos.ClientDTO;
import br.com.rodrigodoe.zupbank.data.forms.ClientForm;
import br.com.rodrigodoe.zupbank.services.AddressService;
import br.com.rodrigodoe.zupbank.services.ClientService;
import br.com.rodrigodoe.zupbank.services.FileStoreService;
import br.com.rodrigodoe.zupbank.services.api.S3Services;
import br.com.rodrigodoe.zupbank.utils.ClassConverterBuilder;

@WebMvcTest
public class ClientControllerTests {

	private final String VALID_CLIENT = "{\n\"firstName\": \"Rodrigo\",\n \"birthDay\":\"2001-06-18\",\n \"lastName\": \"Carvalho\",\n	\"cpf\": \"91115434004\",\n  \"email\": \"aaa@aaa.com\"\n }";

	@Autowired
	private MockMvc mockMVC;

	@MockBean
	private ClientService clientService;
	@MockBean
	private AddressService adressService;
	@MockBean
	private FileStoreService fileStorageService;
	@MockBean
	private S3Services s3services;

	@Test
	@DisplayName("Should create a client and return status 201")
	public void createAclientSucess() throws Exception {

		ClientDTO dto = ClientBuilder.build();
		ClientForm form = ClassConverterBuilder.build(dto, ClientForm.class);
		when(this.clientService.create(form)).thenReturn(dto);

		this.mockMVC
				.perform(post("/clients").content(VALID_CLIENT).header(HttpHeaders.CONTENT_TYPE,
						MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.cpf", notNullValue()))
				.andExpect(jsonPath("$.firstName", is("RODRIGO")));
	}

	@Test
	@DisplayName("Should find a list of clients")
	public void findAllClients() throws Exception {

		List<ClientDTO> lista = ClientBuilder.buildList();

		when(this.clientService.findAll()).thenReturn(lista);

		this.mockMVC.perform(get("/clients").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());

	}

}
