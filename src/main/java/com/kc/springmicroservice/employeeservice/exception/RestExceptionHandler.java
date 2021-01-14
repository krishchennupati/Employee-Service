package com.kc.springmicroservice.employeeservice.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.kc.springmicroservice.employeeservice.EmployeeNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler {

	private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RestExceptionHandler.class);

	
	@ExceptionHandler(BindException.class)
	public ResponseEntity<ExceptionResponse> handleBindException(BindException ex, WebRequest webRequest) {

		logger.error("Entered Bind violation");
		StringBuilder errorMessage = new StringBuilder();
		ex.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()+"."));
		return new ResponseEntity<ExceptionResponse>(ExceptionResponse.builder().timeStamp(new Date()).message(errorMessage.toString()).uri(webRequest.getDescription(false)).build(), HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<ExceptionResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex,
			WebRequest webRequest) {

		logger.error("Entered EmployeeNotFoundException");
		return new ResponseEntity<ExceptionResponse>(
				ExceptionResponse.builder().timeStamp(new Date()).message(ex.getMessage())
						.details(webRequest.getDescription(false)).uri(webRequest.getDescription(false)).build(),
				HttpStatus.BAD_REQUEST);
	}

}
