package com.rest.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.demo.dao.RoleDao;
import com.rest.demo.entity.Role;

@Service
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	public Role SaveRole(Role role) {
		return roleDao.save(role);
	}
}
