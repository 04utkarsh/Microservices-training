package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.ExceptionResponse;
import com.example.demo.entity.Product;
import com.example.demo.exception.CustomExceptionHandler;
import com.example.demo.exception.NoResultFoundException;

@ExtendWith(MockitoExtension.class)
public class ExceptionResponseTest {
	
	@InjectMocks
	private CustomExceptionHandler exceptionHandler;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testHandleAllExceptions() {
		NoResultFoundException ex=new NoResultFoundException("No result found");
		ResponseEntity<Object> response=exceptionHandler.handleAllExceptions(ex);
		
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
	}
	
	@Test
	void testSetterMethods() {
		ExceptionResponse er =new ExceptionResponse();
		ExceptionResponse exceptionResponse = new ExceptionResponse(404,"Not Found");
		exceptionResponse.setStatusCode(405);
		exceptionResponse.setMessage("Not Authorized");
		
		assertEquals(0,er.getStatusCode());
		assertEquals(405,exceptionResponse.getStatusCode());
		assertEquals("Not Authorized",exceptionResponse.getMessage());
	}

}
