package br.com.rodrigodoe.zupbank.data.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.MapKey;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.WebFluxLink;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.rodrigodoe.zupbank.annotations.NotUnderAge;

@JsonInclude(Include.NON_NULL)
public class ClientDTO extends RepresentationModel  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7758676040163998645L;


	private Long id;
	
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
    @JsonFormat(pattern="yyyy-MM-dd")
	@NotUnderAge
	private LocalDate birthDay;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@CPF
	private String cpf;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	


}
