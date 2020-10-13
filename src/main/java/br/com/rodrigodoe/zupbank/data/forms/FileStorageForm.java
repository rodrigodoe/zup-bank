package br.com.rodrigodoe.zupbank.data.forms;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FileStorageForm implements Serializable {

	private static final long serialVersionUID = 1817250501284831856L;

	@NotNull
	private String filename;

}
