package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

	@ExceptionHandler(exception=Exception.class)
	public Map<String, String> handler(MethodArgumentNotValidException a){
		Map<String,String> error=new HashMap<>();
		List<ObjectError> list=a.getBindingResult().getAllErrors();
		list.forEach((err)->{
			String fieldName=((FieldError)err).getField();
			String mss=err.getDefaultMessage();
			error.put(fieldName,mss);
		});
		return error;
	}
}