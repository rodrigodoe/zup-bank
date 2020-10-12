package br.com.rodrigodoe.zupbank.exceptions;

public class MyUnprocessableEntityException extends RuntimeException {
	
	private static final long serialVersionUID = 1298051388009571661L;

	public MyUnprocessableEntityException(String message) {
		super(message);
	}

}
