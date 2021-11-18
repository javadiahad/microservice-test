/**
 * 
 */
package com.green.product1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ahad
 *
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private final String errorCode;


	public DataNotFoundException() {
		super();
		errorCode="";

	}

	public DataNotFoundException(String message) {
		super(message);
		errorCode="";

	}

	public DataNotFoundException(String errorCode, String message,Object ... params) {		
		super(String.format(message,params));
		this.errorCode=errorCode;

	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

}
