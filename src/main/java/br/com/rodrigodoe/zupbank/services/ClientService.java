package br.com.rodrigodoe.zupbank.services;

import java.time.format.DateTimeParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.sendgrid.Response;

import br.com.rodrigodoe.zupbank.data.dtos.ClientDTO;
import br.com.rodrigodoe.zupbank.data.forms.ClientForm;
import br.com.rodrigodoe.zupbank.data.models.Client;
import br.com.rodrigodoe.zupbank.exceptions.DuplicateConstraintException;
import br.com.rodrigodoe.zupbank.exceptions.MyUnprocessableEntityException;
import br.com.rodrigodoe.zupbank.repositories.ClientRepository;
import br.com.rodrigodoe.zupbank.services.api.SendGridService;
import br.com.rodrigodoe.zupbank.utils.ClassConverterBuilder;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private SendGridService sgService;

	public ClientDTO create(@Valid ClientForm clientForm) {

		try {

			var client = ClassConverterBuilder.build(clientForm, Client.class);

			var existingClient = clientRepository.findByEmail(clientForm.getEmail());

			if (existingClient != null) {
				throw new DuplicateConstraintException("Email existente no banco de dados!");
			}

			Client newClient = clientRepository.save(client);
			return ClassConverterBuilder.build(newClient, ClientDTO.class);

		} catch (DateTimeParseException e) {
			throw new RuntimeException("Formato de data invalida");
		}

	}

	public List<ClientDTO> findAll() {
		List<Client> clients = this.clientRepository.findAll();
		return ClassConverterBuilder.buildList(clients, ClientDTO.class);
	}

	public ClientDTO findById(Long id) {
		var client = clientRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Nenhum registro encontrado para o cliente informado"));

		return ClassConverterBuilder.build(client, ClientDTO.class);
	}

	public Client findEntityById(Long id) {
		var client = clientRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Nenhum registro encontrado para o cliente informado"));
		return client;
	}

	public ClientDTO update(Long id, @Valid ClientForm clientForm) {
		var client = findEntityById(id);
		client.setCpf(clientForm.getCpf());
		client.setBirthDay(clientForm.getBirthDay());
		client.setEmail(clientForm.getEmail());
		client.setFirstName(clientForm.getFirstName());
		client.setLastName(clientForm.getLastName());
		return ClassConverterBuilder.build(clientRepository.save(client), ClientDTO.class);
	}

	public void delete(Long id) {
		var client = findEntityById(id);
		clientRepository.deleteById(client.getId());
	}

	public ClientDTO validade(Long id) {
		Client enttiy = this.clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Proposta nao encontrada"));

		ClientDTO dto = ClassConverterBuilder.build(enttiy, ClientDTO.class);

		if (dto.getAddress() == null) {
			throw new MyUnprocessableEntityException("Endereco nao cadastrado na proposta!");
		}

		if (dto.getFile() == null) {
			throw new MyUnprocessableEntityException("Foto da frente do CPF nao enviada!");
		}

		return dto;

	}

	public Response confirm(Long id) {
		ClientDTO dto = this.validade(id);
		return sgService.sendMailConfirmation("rrodgcar@gmail.com", dto.getEmail());

	}

}
