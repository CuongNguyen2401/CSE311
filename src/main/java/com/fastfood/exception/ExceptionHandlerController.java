package com.fastfood.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ExceptionDetail> UserNotFound(UserNotFoundException ex, WebRequest request) {
		ExceptionDetail detail = new ExceptionDetail(new Date().toString(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionDetail> resourceNotFound(ResourceNotFoundException e, WebRequest request) {
		ExceptionDetail detail = new ExceptionDetail(new Date().toString(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
	}
}
