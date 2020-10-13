package br.com.rodrigodoe.zupbank.validations;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.rodrigodoe.zupbank.annotations.ZipCode;

public class ZipCodeValidator implements ConstraintValidator<ZipCode, String>{
	
	private Pattern pattern = 
	          Pattern.compile("[0-9]{5}-[0-9]{3}");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		
	    return value != null ? pattern.matcher(value).matches(): false;
	}

}
