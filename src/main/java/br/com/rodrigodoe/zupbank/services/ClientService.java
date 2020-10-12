package br.com.rodrigodoe.zupbank.services;

import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.rodrigodoe.zupbank.data.dtos.ClientDTO;
import br.com.rodrigodoe.zupbank.data.models.Client;
import br.com.rodrigodoe.zupbank.exceptions.DuplicateConstraintException;
import br.com.rodrigodoe.zupbank.exceptions.MyUnprocessableEntityException;
import br.com.rodrigodoe.zupbank.repositories.ClientRepository;
import br.com.rodrigodoe.zupbank.utils.ClassConverterUtils;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public ClientDTO create(ClientDTO clientDto) {

		try {

			var client = ClassConverterUtils.convert(clientDto, Client.class);

			var existingClient = clientRepository.findByEmail(clientDto.getEmail());

			if (existingClient != null) {
				throw new DuplicateConstraintException("Email existente no banco de dados!");
			}

			Client newClient = clientRepository.save(client);
			return ClassConverterUtils.convert(newClient, ClientDTO.class);

		} catch (DateTimeParseException e) {
			throw new RuntimeException("Formato de data invalida");
		}

	}

	public List<ClientDTO> findAll() {
		List<Client> clients = this.clientRepository.findAll();
		return ClassConverterUtils.convertList(clients, ClientDTO.class);
	}

	public ClientDTO findById(Long id) {
		var client = clientRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Nenhum registro encontrado para o cliente informado"));

		return ClassConverterUtils.convert(client, ClientDTO.class);
	}

	public Client findEntityById(Long id) {
		var client = clientRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Nenhum registro encontrado para o cliente informado"));
		return client;
	}

	public ClientDTO update(Long id, ClientDTO clientDto) {
		var client = findEntityById(id);
		client.setCpf(clientDto.getCpf());
		client.setBirthDay(clientDto.getBirthDay());
		client.setEmail(clientDto.getEmail());
		client.setFirstName(clientDto.getFirstName());
		client.setLastName(clientDto.getLastName());
		return ClassConverterUtils.convert(clientRepository.save(client), ClientDTO.class);
	}

	public void delete(Long id) {
		var client = findEntityById(id);
		clientRepository.deleteById(client.getId());
	}

	public ClientDTO validade(Long id) {
		Client enttiy = this.clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Proposta nao encontrada"));

		ClientDTO dto = ClassConverterUtils.convert(enttiy, ClientDTO.class);

		if (dto.getAddress() == null) {
			throw new MyUnprocessableEntityException("Endereco nao cadastrado na proposta!");
		}

		if (dto.getFile() == null) {
			throw new MyUnprocessableEntityException("Foto da frente do CPF nao enviada!");
		}

		return dto;

	}

	public ClientDTO confirm(Long id) {
		ClientDTO dto = this.validade(id);
		return null;
	}

}
