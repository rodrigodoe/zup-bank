package br.com.rodrigodoe.zupbank.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.rodrigodoe.zupbank.validations.PastYearsValidator;


@Constraint(validatedBy = PastYearsValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PastYears{
	
    String message() default "Usuário cadastrado deve idade igual ou maior a 18 anos";
        
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
 

}
