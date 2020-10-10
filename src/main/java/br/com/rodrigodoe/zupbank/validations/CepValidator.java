package br.com.rodrigodoe.zupbank.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.rodrigodoe.zupbank.annotations.ZipCode;

public class CepValidator implements ConstraintValidator<ZipCode, String>{
	
	private Pattern pattern = 
	          Pattern.compile("[0-9]{5}-[0-9]{3}");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Matcher m = pattern.matcher(value);
	    return m.matches();
	}

}
