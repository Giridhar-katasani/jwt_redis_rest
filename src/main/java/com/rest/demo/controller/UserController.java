package com.rest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.demo.entity.User;
import com.rest.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@GetMapping("/forUser")
	public String getUser() {
		System.out.println("form for User");
		return "From the user";
	}
	
	@GetMapping("/forAdmin")
	public String getAdmin() {
		return "From the admin";
	}
}
