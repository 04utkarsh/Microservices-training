package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.entity.ExceptionResponse;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NoResultFoundException.class)
	public final ResponseEntity<Object> handleAllExceptions(NoResultFoundException ex){
		ExceptionResponse response = new ExceptionResponse(404,ex.getMessage());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	

}
