package br.com.rodrigodoe.zupbank.data.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.rodrigodoe.zupbank.enums.ClientStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper = false)
public class ClientDTO extends RepresentationModel<ClientDTO> implements Serializable {

	private static final long serialVersionUID = 7758676040163998645L;

	@EqualsAndHashCode.Include
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate birthDay;
	private String cpf;
	@JsonIgnore
	private AddressDTO address;
	@JsonIgnore
	private FileStorageDTO file;

	private ClientStatus status;

}
