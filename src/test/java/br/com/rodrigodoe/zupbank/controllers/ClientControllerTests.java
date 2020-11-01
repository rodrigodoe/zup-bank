package br.com.rodrigodoe.zupbank.controllers;

import static br.com.rodrigodoe.zupbank.builder.ClientBuilder.buildClientDTO;
import static br.com.rodrigodoe.zupbank.builder.ClientBuilder.buildList;
import static br.com.rodrigodoe.zupbank.builder.ClientBuilder.VALID_CLIENT_REQUEST;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import br.com.rodrigodoe.zupbank.data.dtos.ClientDTO;
import br.com.rodrigodoe.zupbank.data.forms.ClientForm;
import br.com.rodrigodoe.zupbank.services.ClientService;

@DisplayName("Client Controller Test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTests {
	
	private final String BASE_URL = "/clients";

	@Autowired
	private MockMvc mockMVC;

	@MockBean
	private ClientService clientService;

	@Test
	@DisplayName("Should find a list of clients")
	public void findAllClients() throws Exception {

		List<ClientDTO> lista = buildList();

		when(this.clientService.findAll()).thenReturn(lista);

		this.mockMVC
			   .perform(get(BASE_URL)
			   .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
			   .andDo(print())
			   .andExpect(status().isOk());

	}

	@Test
	@DisplayName("Should create a client and return status 201")
	public void createAclientSucess() throws Exception {
		
		ClientDTO dto = buildClientDTO();
		
		when(this.clientService.create(any(ClientForm.class))).thenReturn(dto);

		this.mockMVC
			  .perform(post(BASE_URL)
			  .content(VALID_CLIENT_REQUEST)
			  .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.cpf", notNullValue()))
				.andExpect(jsonPath("$.firstName", is("RODRIGO")));
	}

}
