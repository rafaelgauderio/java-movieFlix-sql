package com.rafaeldeluca.movieflix.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rafaeldeluca.movieflix.services.exceptions.DataBaseException;
import com.rafaeldeluca.movieflix.services.exceptions.ForbiddenException;
import com.rafaeldeluca.movieflix.services.exceptions.ResourceNotFoundException;
import com.rafaeldeluca.movieflix.services.exceptions.UnauthorizedException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException error, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND; // erro 404
		StandardError erro = new StandardError();
		erro.setStatus(status.value());
		erro.setTimestamp(Instant.now());
		erro.setError("Recurso não encontrado");
		erro.setMessage(error.getMessage());
		erro.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}

	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> database(DataBaseException error, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST; // erro 400
		StandardError erro = new StandardError();
		erro.setStatus(status.value());
		erro.setTimestamp(Instant.now());
		erro.setError("Exceção de bando de dados");
		erro.setMessage(error.getMessage());
		erro.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(erro);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validationMethod(MethodArgumentNotValidException error,
			HttpServletRequest request) {

		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; // codigo 422
		ValidationError erro = new ValidationError();
		erro.setTimestamp(Instant.now());
		erro.setStatus(status.value());
		erro.setTimestamp(Instant.now());
		erro.setTimestamp(Instant.now());

		erro.setError("Exceção de validação. Não foi possível criar ou atualizar o objeto.");
		erro.setMessage(error.getMessage());
		erro.setPath(request.getRequestURI());

		for (FieldError field : error.getBindingResult().getFieldErrors()) {
			erro.addError(field.getField(), field.getDefaultMessage());
		}

		return ResponseEntity.status(status).body(erro);
	}

	// erro 403
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<OAuthCustomError> forbiden(ForbiddenException error, HttpServletRequest request) {

		OAuthCustomError erro = new OAuthCustomError("Forbidden", error.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
	}

	// erro 401
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<OAuthCustomError> unauthorized(UnauthorizedException error, HttpServletRequest request) {

		OAuthCustomError erro = new OAuthCustomError("Unauthorized", error.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erro);
	}

}