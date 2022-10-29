package com.nonamed.farm.global.exception.handler;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nonamed.farm.global.exception.NonamedException;

@RestControllerAdvice
public class NonamedExceptionHandler {

	@ExceptionHandler(NonamedException.class)
	protected ResponseEntity<ErrorResponse> handlerDcsException(final NonamedException e) {
		return new ResponseEntity<>(new ErrorResponse(e.getErrorCode().getStatus(), e.getMessage()), HttpStatus.valueOf(e.getErrorCode().getStatus()));
	}

	@ExceptionHandler(NullPointerException.class)
	protected ResponseEntity<ErrorResponse> HandleNullPointerException(final NullPointerException e) {
		return new ResponseEntity<>(new ErrorResponse(400, e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ValidationException.class)
	protected ResponseEntity<ErrorResponse> HandleValidationException(final ValidationException e) {
		return new ResponseEntity<>(new ErrorResponse(400, e.getMessage()), HttpStatus.BAD_REQUEST);
	}

}
