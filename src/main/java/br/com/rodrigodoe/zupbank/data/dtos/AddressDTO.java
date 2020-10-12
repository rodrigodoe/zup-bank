package br.com.rodrigodoe.zupbank.data.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.rodrigodoe.zupbank.annotations.ZipCode;
import br.com.rodrigodoe.zupbank.data.models.Client;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(Include.NON_NULL)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AddressDTO extends RepresentationModel<AddressDTO> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4431326883533174354L;

	@EqualsAndHashCode.Include
	private Long id;

	@ZipCode
	@NotNull
	private String zipcode;

	@NotNull
	private String addressLine1;

	@NotNull
	private String addressLine2;

	@NotNull
	private String suburb;

	@NotNull
	private String city;

	@NotNull
	private String state;
	@JsonIgnore
	private Client client;

}
