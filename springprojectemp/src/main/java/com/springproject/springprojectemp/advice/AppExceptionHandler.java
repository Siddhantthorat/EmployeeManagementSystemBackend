package com.springproject.springprojectemp.advice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;



import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springproject.springprojectemp.exception.ResourceNotFoundException;

@RestControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(NoSuchElementException.class)
	public Map<String,String> handleBusinessException(NoSuchElementException ex)
	{
		Map<String,String> errorMap=new HashMap<>();
		errorMap.put("errorMessage",ex.getMessage());
		return errorMap;
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public Map<String,String> handleBusinessException(ResourceNotFoundException ex)
	{
		Map<String,String> errorMap=new HashMap<>();
		errorMap.put("errorMessage",ex.getMessage());
		return errorMap;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleUnexpectedException(MethodArgumentNotValidException ex)
	{
		Map<String,String> errorMap=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error->{
			errorMap.put(error.getField(),error.getDefaultMessage());
		});

		return errorMap;
	}
	
	
}

