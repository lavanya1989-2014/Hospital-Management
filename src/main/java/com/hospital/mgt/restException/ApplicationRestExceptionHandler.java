package com.hospital.mgt.restException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationRestExceptionHandler {

	//Add an exception handler for customerNotFound
	@ExceptionHandler
	public ResponseEntity<ApplicationErrorResponse> handleException(RecordNotFoundRestException exc){
		
		ApplicationErrorResponse error=new ApplicationErrorResponse(
									HttpStatus.NOT_FOUND.value(),
									exc.getMessage(), 
									System.currentTimeMillis());
		
		return new  ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ApplicationErrorResponse> handleException(DuplicateRecordRestException exc){
		
		ApplicationErrorResponse error=new ApplicationErrorResponse(
									HttpStatus.ALREADY_REPORTED.value(),
									exc.getMessage(), 
									System.currentTimeMillis());
		
		return new  ResponseEntity<>(error,HttpStatus.ALREADY_REPORTED);
	}
	
	//Add another exception handler.... to catch any exception(catch all)
	
	@ExceptionHandler
	public ResponseEntity<ApplicationErrorResponse> handleException(Exception exc){
		
		ApplicationErrorResponse error=new ApplicationErrorResponse(
									HttpStatus.BAD_REQUEST.value(),
									exc.getMessage(), 
									System.currentTimeMillis());
		
		return new  ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
}
