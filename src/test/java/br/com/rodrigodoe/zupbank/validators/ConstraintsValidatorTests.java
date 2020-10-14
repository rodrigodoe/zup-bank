package br.com.rodrigodoe.zupbank.validators;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.DisplayName;

import com.mysql.cj.exceptions.AssertionFailedException;

import br.com.rodrigodoe.zupbank.data.forms.ClientForm;

public class ConstraintsValidatorTests {

	private Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	@DisplayName("Should validate successful form")
	public void testClientSuccess() {
		ClientForm form = new ClientForm();
		form.setCpf("06234132480");
		form.setBirthDay(LocalDate.parse("2001-06-18"));
		form.setEmail("aaa@aaa.com");
		form.setFirstName("RODRIGO");
		form.setLastName("CARVALHO");
		Set<ConstraintViolation<ClientForm>> violations = validator.validate(form);
		assertTrue(violations.isEmpty());
	}

	@Test
	@DisplayName("Should validate cpf")
	public void validateCPF() {
		ClientForm form = new ClientForm();
		form.setBirthDay(LocalDate.parse("2001-06-18"));
		form.setEmail("aaa@aaa.com");
		form.setFirstName("RODRIGO");
		form.setLastName("CARVALHO");
		Set<ConstraintViolation<ClientForm>> violations = validator.validate(form);
		assertFalse(violations.isEmpty());
	}

	@Test
	@DisplayName("Should validate email")
	public void validateEMAIL() {
		ClientForm form = new ClientForm();
		form.setCpf("06234132480");
		form.setBirthDay(LocalDate.parse("2001-06-18"));
		form.setFirstName("RODRIGO");
		form.setLastName("CARVALHO");
		Set<ConstraintViolation<ClientForm>> violations = validator.validate(form);
		assertFalse(violations.isEmpty());
	}

	@Test
	@DisplayName("Should validate age")
	public void validadeValidAge() {
		ClientForm form = new ClientForm();
		form.setCpf("06234132480");
		form.setBirthDay(LocalDate.parse("2003-06-18"));
		form.setEmail("aa@aaa.com");
		form.setFirstName("RODRIGO");
		form.setLastName("CARVALHO");
		Set<ConstraintViolation<ClientForm>> violations = validator.validate(form);
		assertFalse(violations.isEmpty());
	}

}
