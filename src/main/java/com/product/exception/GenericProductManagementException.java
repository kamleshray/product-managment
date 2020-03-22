package com.product.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GenericProductManagementException extends RuntimeException  {

	private static final long serialVersionUID = 1L;
	private ExceptionResponse exceptionResponse;
	
}
