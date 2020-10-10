package br.com.rodrigodoe.zupbank.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.rodrigodoe.zupbank.validations.NotUnderAgeValidator;


@Constraint(validatedBy = NotUnderAgeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotUnderAge{
	
    String message() default "Usuário cadastrado deve idade igual ou maior a 18 anos";
        
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
 

}
