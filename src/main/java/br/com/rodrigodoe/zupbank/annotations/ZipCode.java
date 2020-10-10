package br.com.rodrigodoe.zupbank.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.rodrigodoe.zupbank.validations.CepValidator;

@Constraint(validatedBy = CepValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ZipCode {
  
  String message() default "Cep inválido use padrão 00000-00";
  Class<?>[] groups() default { };
  Class<? extends Payload>[] payload() default { };

}