//package com.rest.demo.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import com.rest.demo.entity.StudentErrorResponse;
//import com.rest.demo.entity.User;
//import com.rest.demo.exceptions.StudentNotFoundException;
//
//@ControllerAdvice
//public class GlobalExceptionController {
//	
//	@ExceptionHandler
//	public ResponseEntity<StudentErrorResponse> handleException(User exe) {
//		StudentErrorResponse stu = new StudentErrorResponse();
//		stu.setStatus(HttpStatus.NOT_FOUND.value());
//		stu.setMessage(exe.getMessage());
//		stu.setTimeStamp(System.currentTimeMillis());
//		System.out.println(stu.toString());
//		return new ResponseEntity<StudentErrorResponse>(stu, HttpStatus.NOT_FOUND);
//	}
//}
