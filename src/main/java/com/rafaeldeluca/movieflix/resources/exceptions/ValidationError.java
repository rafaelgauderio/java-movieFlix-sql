package com.rafaeldeluca.movieflix.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> erros = new ArrayList<>();

	public List<FieldMessage> getErrors() {
		return erros;
	}

	public void addError(String fieldName, String message) {
		FieldMessage CampoMensagem = new FieldMessage(fieldName, message);
		erros.add(CampoMensagem);
	}

}
