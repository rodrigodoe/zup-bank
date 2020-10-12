package br.com.rodrigodoe.zupbank.data.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.rodrigodoe.zupbank.annotations.NotUnderAge;
import lombok.Data;
import lombok.EqualsAndHashCode;


@JsonInclude(Include.NON_NULL)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClientDTO extends RepresentationModel<ClientDTO> implements Serializable {

	private static final long serialVersionUID = 7758676040163998645L;

	@EqualsAndHashCode.Include
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
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotUnderAge
	@NotNull
	private LocalDate birthDay;
	@NotNull
	@CPF
	private String cpf;

}
