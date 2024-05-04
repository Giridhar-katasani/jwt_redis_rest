package com.rest.demo.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rest.demo.dao.UserDao;
import com.rest.demo.entity.User;
import com.rest.demo.entity.Role;
import com.rest.demo.exceptions.UserNotFoundException;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserDao userDao;
	
	public User saveUser(User user) {
		return userDao.save(user);
	}
	
//	public Optional<User findUser(String userName) {
//		return userDao.findById(userName);
//	}

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> userOptional = userDao.findById(username);
		System.out.println(userOptional+" "+42);
		return userDao.findById(username).orElseThrow(() ->  new 
				UserNotFoundException("User not found with username: " + username));
	}
}
