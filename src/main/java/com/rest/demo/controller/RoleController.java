package com.rest.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.demo.entity.Role;
import com.rest.demo.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	@PostMapping("/save")
	public Role save(@RequestBody Role role) {
		return roleService.SaveRole(role);
	}
}
