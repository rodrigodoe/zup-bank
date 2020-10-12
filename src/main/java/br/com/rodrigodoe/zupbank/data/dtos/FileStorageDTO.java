package br.com.rodrigodoe.zupbank.data.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.rodrigodoe.zupbank.data.models.Client;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(Include.NON_NULL)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FileStorageDTO extends RepresentationModel<FileStorageDTO> implements Serializable {

	private static final long serialVersionUID = -2076251076082242246L;

	@EqualsAndHashCode.Include
	private Long id;
	@JsonIgnore
	private Client client;

	@NotNull
	private String filename;

}
