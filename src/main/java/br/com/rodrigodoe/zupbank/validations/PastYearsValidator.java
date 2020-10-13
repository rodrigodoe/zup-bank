package br.com.rodrigodoe.zupbank.validations;


import java.time.LocalDate;
import java.time.Period;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.rodrigodoe.zupbank.annotations.PastYears;

public class PastYearsValidator implements ConstraintValidator<PastYears, LocalDate>{

	
	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
				
		LocalDate date = value;
		
		LocalDate dateNow = LocalDate.now();

		Period period = Period.between(date, dateNow);

		return period.getYears() >= 18;
		
	}

}
