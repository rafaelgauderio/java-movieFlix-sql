package com.rafaeldeluca.movieflix.services.exceptions;

public class UnauthorizedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public UnauthorizedException(String message) {
		// erro 401 , erro de token inválido
		super(message);
	}

}