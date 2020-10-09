package br.com.rodrigodoe.zupbank.data.dto;

import java.time.LocalDate;


import br.com.rodrigodoe.zupbank.data.model.Client;

public class ClientDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate birthDay;
	private String cpf;

	public ClientDTO(Client client) {
		this.id = client.getId();
		this.firstName = client.getFirstName();
		this.lastName = client.getLastName();
		this.email = client.getEmail();
		this.birthDay = client.getBirthDay();
		this.cpf = client.getCpf();
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public String getCpf() {
		return cpf;
	}
	
	
	
	
	

}
