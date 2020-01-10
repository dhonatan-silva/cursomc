package com.acme.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.acme.cursomc.services.exception.ObjectNofFoundException;

@ControllerAdvice
public class ResourceExcptionHandler {
	
	@ExceptionHandler(ObjectNofFoundException.class)
	public ResponseEntity<StandardError> objectNofFound(ObjectNofFoundException e, HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
		
	}

}
