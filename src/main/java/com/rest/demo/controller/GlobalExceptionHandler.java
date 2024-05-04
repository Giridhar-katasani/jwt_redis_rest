package com.rest.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.rest.demo.entity.ErrorResponse;
import com.rest.demo.exceptions.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	public GlobalExceptionHandler() {

	}

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	// Specify the exception type the handler should apply to
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(UserNotFoundException ue, WebRequest request) {

		log.error("User not found: " + request.getDescription(false), ue);
		ErrorResponse er = new ErrorResponse();
		er.setMessage(ue.getMessage());
		er.setStatus(HttpStatus.NOT_FOUND.value());
		er.setTimeStamp(System.currentTimeMillis());

		String requestedUri = request.getDescription(false);

		return new ResponseEntity<ErrorResponse>(er, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorResponse> badCredentialhandleException(BadCredentialsException e, WebRequest request) {

		log.error("User not found: " + request.getDescription(false), e);
		ErrorResponse er = new ErrorResponse();
		er.setMessage(e.getMessage());
		er.setStatus(HttpStatus.NOT_FOUND.value());
		String requestedUri = request.getDescription(false);

		er.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<ErrorResponse>(er, HttpStatus.NOT_FOUND);
	}
}
