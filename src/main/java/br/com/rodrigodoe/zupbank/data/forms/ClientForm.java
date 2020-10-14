package br.com.rodrigodoe.zupbank.data.forms;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.rodrigodoe.zupbank.annotations.PastYears;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClientForm implements Serializable {

	private static final long serialVersionUID = 2012963711330474199L;

	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@Email
	@NotEmpty
	private String email;
	@PastYears
	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate birthDay;
	@NotEmpty
	@CPF
	private String cpf;

}
