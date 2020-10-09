package br.com.rodrigodoe.zupbank.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import br.com.rodrigodoe.zupbank.data.dto.ClientDTO;
import br.com.rodrigodoe.zupbank.data.forms.ClientForm;
import br.com.rodrigodoe.zupbank.data.model.Client;
import br.com.rodrigodoe.zupbank.exceptions.DuplicateConstraintException;
import br.com.rodrigodoe.zupbank.exceptions.InvalidAgeException;
import br.com.rodrigodoe.zupbank.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;

	public ClientDTO create(ClientForm clientForm) {

		try {

			Client client = clientForm.ConvertToEntity();

			LocalDate dataAtual = LocalDate.now();

			Period period = Period.between(client.getBirthDay(), dataAtual);

			if (period.getYears() < 18) {
				throw new InvalidAgeException("Usuario menor de 18 anos!");
			}

			Client existingClient = clientRepository.findByEmail(clientForm.getEmail());

			if (existingClient != null) {
				throw new DuplicateConstraintException("Email existente no banco de dados!");
			}

			Client newClient = clientRepository.save(client);
			return new ClientDTO(newClient);

		} catch (DateTimeParseException e) {
			throw new RuntimeException("Formato de data invalida");
		}

	}

}
