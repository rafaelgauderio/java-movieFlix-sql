package com.rafaeldeluca.movieflix.services.exceptions;

public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	// erro 403, token é valido, mas o recurso está de bloqueado devido ao perfil dele não ter acesso a esse recurso
	public ForbiddenException (String message) {		
		super(message);
	}

}