/**
 * 
 */
package com.green.product1.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Ahad
 *
 */
@ControllerAdvice
@RestController
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler{
	
	 @ExceptionHandler(ServiceBusinessException.class)
	  public final ResponseEntity<ExceptionInfo> handleNotFoundException(ServiceBusinessException ex, WebRequest request) {
		 ExceptionInfo exceptionResponse = new ExceptionInfo(new Date(),HttpStatus.BAD_REQUEST,ex.getErrorCode(), request.getDescription(false)+ex.getMessage());
	    return new ResponseEntity<ExceptionInfo>(exceptionResponse, HttpStatus.BAD_REQUEST);
	  }
	 

	 @ExceptionHandler(DataNotFoundException.class)
	  public final ResponseEntity<ExceptionInfo> handleNotFoundException(DataNotFoundException ex, WebRequest request) {
		 ExceptionInfo exceptionResponse = new ExceptionInfo(new Date(),HttpStatus.NOT_FOUND,ex.getErrorCode(), request.getDescription(false)+ex.getMessage());
	    return new ResponseEntity<ExceptionInfo>(exceptionResponse, HttpStatus.NOT_FOUND);
	  }

}
