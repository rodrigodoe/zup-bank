package br.com.rodrigodoe.zupbank.services;

import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.rodrigodoe.zupbank.data.dtos.ClientDTO;
import br.com.rodrigodoe.zupbank.data.models.Client;
import br.com.rodrigodoe.zupbank.exceptions.DuplicateConstraintException;
import br.com.rodrigodoe.zupbank.repositories.ClientRepository;
import br.com.rodrigodoe.zupbank.utils.ClientConverterUtils;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public ClientDTO create(ClientDTO clientDto) {

		try {

			var client = ClientConverterUtils.convertToEntity(clientDto);

			var existingClient = clientRepository.findByEmail(clientDto.getEmail());

			if (existingClient != null) {
				throw new DuplicateConstraintException("Email existente no banco de dados!");
			}

			Client newClient = clientRepository.save(client);
			return ClientConverterUtils.convertToDto(newClient);

		} catch (DateTimeParseException e) {
			throw new RuntimeException("Formato de data invalida");
		}

	}

	public List<ClientDTO> findAll() {
		List<Client> clients = this.clientRepository.findAll();
		return ClientConverterUtils.convertList(clients, ClientDTO.class);
	}

	public ClientDTO findById(Long id) {
		var client = clientRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Nenhum registro encontrado para o cliente informado"));

		return ClientConverterUtils.convertToDto(client);
	}

	public Client findEntityById(Long id) {
		var client = clientRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Nenhum registro encontrado para o cliente informado"));
		return client;
	}

	public ClientDTO update(ClientDTO clientDto) {
		var client = findEntityById(clientDto.getId());
		client.setCpf(clientDto.getCpf());
		client.setBirthDay(clientDto.getBirthDay());
		client.setEmail(clientDto.getEmail());
		client.setFirstName(clientDto.getFirstName());
		client.setLastName(clientDto.getLastName());
		return ClientConverterUtils.convertToDto(clientRepository.save(client));
	}

	public void delete(Long id) {
		var client = findEntityById(id);
		clientRepository.deleteById(client.getId());
	}

}
