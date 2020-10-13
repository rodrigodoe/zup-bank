package br.com.rodrigodoe.zupbank.data.forms;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.rodrigodoe.zupbank.annotations.ZipCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AddressForm implements Serializable {

	private static final long serialVersionUID = 4708043692541812422L;

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
}
