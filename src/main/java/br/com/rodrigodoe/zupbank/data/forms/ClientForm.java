package br.com.rodrigodoe.zupbank.data.forms;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.rodrigodoe.zupbank.data.model.Client;

public class ClientForm {
	
	@NotNull
	@NotBlank
	private String firstName;
	@NotNull
	@NotBlank
	private String lastName;
	@Email
	@NotNull
	private String email;
	@NotNull
	private LocalDate birthDay;
	
	@NotNull
	@CPF
	private String cpf;
	
	
	
	public Client ConvertToEntity() {
		Client client = new Client();
		client.setFirstName(this.firstName);
		client.setLastName(this.lastName);
		client.setEmail(this.email);
		client.setBirthDay(this.birthDay);
		client.setCpf(this.cpf);

		return client;
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
