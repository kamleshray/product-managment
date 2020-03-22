package com.product.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(value = GenericProductManagementException.class)
	public ResponseEntity<?> handleException(GenericProductManagementException e) {
		return new ResponseEntity<>(e.getExceptionResponse().getMessage(), e.getExceptionResponse().getHttpstatus());
	}
}
