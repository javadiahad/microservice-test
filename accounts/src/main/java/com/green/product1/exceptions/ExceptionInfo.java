/**
 * 
 */
package com.green.product1.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;

/**
 * @author Ahad
 *
 */



public class ExceptionInfo {
    private final Date date ;
    private final HttpStatus httpStatus ;
    private final String errorCode ;
    private final String message ;
    
    
    
	public ExceptionInfo(Date date,HttpStatus httpStatus, String errorCode, String message) {
		super();
		this.date=date;
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.message = message;
	}
	/**
	 * @return the httpStatus
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	@Override
	public String toString() {
		return "ExceptionInfo [httpStatus=" + httpStatus + ", errorCode=" + errorCode + ", message=" + message + "]";
	}
    
    
}