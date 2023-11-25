package com.cg.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.project.exception.AccountException;


@ControllerAdvice
public class AccountExceptionHandler {

	@ExceptionHandler({AccountException.class})
	public ResponseEntity<String> handleException(Exception ex){
		return new ResponseEntity<String>("Error: " + ex.getMessage(), HttpStatus.CONFLICT);
	}
}
