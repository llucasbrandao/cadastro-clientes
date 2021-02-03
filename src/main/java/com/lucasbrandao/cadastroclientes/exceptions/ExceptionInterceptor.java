package com.lucasbrandao.cadastroclientes.exceptions;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionInterceptor {
	
	@ExceptionHandler(value = {RuntimeException.class})
	public final ResponseEntity<ExceptionModel> handleRunTimeException(RuntimeException ex, WebRequest request) {
		ExceptionModel exceptionModel = generateException(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<>(exceptionModel, exceptionModel.getHttpStatus());
	}
	
	@ExceptionHandler(value = {UserPermissionsException.class})
	public final ResponseEntity<ExceptionModel> handleUserPermissionsException(UserPermissionsException ex, WebRequest request) {
		ExceptionModel exceptionModel = generateException(ex, request, HttpStatus.FORBIDDEN);
		
		return new ResponseEntity<>(exceptionModel, exceptionModel.getHttpStatus());
	}
	
	@ExceptionHandler(value = {NoSuchElementException.class})
	public final ResponseEntity<ExceptionModel> handleNoSuchElementException(RuntimeException ex, WebRequest request) {
		ExceptionModel exceptionModel = generateException(ex, request, HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(exceptionModel, exceptionModel.getHttpStatus());
	}
	
	
	private final ExceptionModel generateException(Exception ex, WebRequest request, HttpStatus httpStatus) {
		return new ExceptionModel(ex.getMessage(), 
				((ServletWebRequest) request).getRequest().getRequestURI(), new Date(), 
				httpStatus);
	}
}