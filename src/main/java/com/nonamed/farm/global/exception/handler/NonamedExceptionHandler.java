package com.nonamed.farm.global.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nonamed.farm.global.exception.NonamedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class NonamedExceptionHandler {

	@ExceptionHandler(NonamedException.class)
	protected ResponseEntity<ErrorResponse> handlerDcsException(final NonamedException e) {
		return new ResponseEntity<>(new ErrorResponse(e.getErrorCode().getStatus(), e.getErrorCode().getMessage()), HttpStatus.valueOf(e.getErrorCode().getStatus()));
	}

	@ExceptionHandler(NullPointerException.class)
	protected ResponseEntity<ErrorResponse> HandleNullPointerException(final NullPointerException e) {
		log.error("[NullPointerException] : " + e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(400, e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorResponse> HandleValidationException(final MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();

		StringBuilder builder = new StringBuilder();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			builder.append(fieldError.getField()).append("(은)는 ");
			builder.append(fieldError.getDefaultMessage()).append("   ");
		}

		log.error("[HandleValidationException] : " + e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(400, builder.toString()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		log.error("[handleHttpRequestMethodNotSupportedException] : " + e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(405, e.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorResponse> handleException(Exception e) {
		log.error("[exception] : " + e.getMessage());
		return new ResponseEntity<>(new ErrorResponse(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
