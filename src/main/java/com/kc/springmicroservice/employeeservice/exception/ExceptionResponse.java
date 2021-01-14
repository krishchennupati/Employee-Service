package com.kc.springmicroservice.employeeservice.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ExceptionResponse {
	
	private Date timeStamp;
	private String message;
	private String details;
	private String uri;
	
	
}
