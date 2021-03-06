package br.com.rodrigodoe.zupbank.exceptions.handlers;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.rodrigodoe.zupbank.exceptions.CustomizedValidationError;
import br.com.rodrigodoe.zupbank.exceptions.DuplicateConstraintException;
import br.com.rodrigodoe.zupbank.exceptions.InvalidAgeException;
import br.com.rodrigodoe.zupbank.exceptions.MyUnprocessableEntityException;

@RestControllerAdvice
public class CustomizedExceptionHandlers {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<CustomizedValidationError> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<CustomizedValidationError> errors = new ArrayList<CustomizedValidationError>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			CustomizedValidationError erro = new CustomizedValidationError(error.getDefaultMessage(),
					((FieldError) error).getField());
			errors.add(erro);
		});
		return errors;
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public CustomizedValidationError handleResourcesNotFoundExceptions(Exception ex) {
		CustomizedValidationError customError = new CustomizedValidationError(ex.getMessage());
		return customError;
	}

	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(MyUnprocessableEntityException.class)
	public CustomizedValidationError handleUnprocessableEntityExceptions(Exception ex) {
		CustomizedValidationError customError = new CustomizedValidationError(ex.getMessage());
		return customError;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { DuplicateConstraintException.class, InvalidAgeException.class })
	public CustomizedValidationError handleDuplicateConstraintException(Exception ex) {
		CustomizedValidationError customError = new CustomizedValidationError(ex.getMessage());
		return customError;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { ValidationException.class })
	public CustomizedValidationError handleValidationException(Exception ex) {
		CustomizedValidationError customError = new CustomizedValidationError(
				"Voce precisa passar um parametro valido para a data");
		return customError;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { HttpMessageNotReadableException.class, DateTimeParseException.class })
	public CustomizedValidationError handleDateTimeParseException(Exception ex) {
		CustomizedValidationError customError = new CustomizedValidationError("Formato de data inválida");
		return customError;
	}

}
