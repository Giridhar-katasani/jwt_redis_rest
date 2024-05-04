package com.rest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.demo.entity.AuthResponse;
import com.rest.demo.entity.JWTrequest;
import com.rest.demo.entity.User;
import com.rest.demo.service.AuthService;
import com.rest.demo.service.RedisTokenService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	@Autowired
	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody User request) {
		System.out.println(request.toString());
		return ResponseEntity.ok(authService.registerUser(request));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> Login(@RequestBody JWTrequest request) throws Exception {
		System.out.println(request.toString());
		return ResponseEntity.ok(authService.authenticate(request));
	}

}
