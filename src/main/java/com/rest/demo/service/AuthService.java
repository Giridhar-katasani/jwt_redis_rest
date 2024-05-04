package com.rest.demo.service;

import java.util.HashSet;

import java.util.Optional;
import java.util.Set;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rest.demo.dao.UserDao;
import com.rest.demo.entity.AuthResponse;
import com.rest.demo.entity.JWTrequest;
import com.rest.demo.entity.Role;
import com.rest.demo.entity.User;
import com.rest.demo.exceptions.UserNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authManger;
	
	@Autowired
	private final RedisTokenService redisTokenService;
	
	public AuthService(RedisTokenService redisTokenService) {
		this.redisTokenService = redisTokenService;
	}
	
	public AuthResponse registerUser(User request) {
		
		User user = new User();
		user.setUsername(request.getUsername());
		user.setFirst_name(request.getFirst_name());
		user.setLast_name(request.getLast_name());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRoles(generateRoles());
		user = userDao.save(user);
		
		String token = jwtService.generateToken(user);
		
		this.redisTokenService.saveToken(request.getUsername(), token);
		return new AuthResponse(token);
	}
	
	public AuthResponse authenticate(JWTrequest request) throws Exception {
		try {
			authManger.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			User user = userDao.findByusername(request.getUsername()).orElseThrow();
			String token = jwtService.generateToken(user);
			this.redisTokenService.saveToken(request.getUsername(), token);
			return new AuthResponse(token);
		} catch (LockedException le) {
		    throw new Exception("Account is locked. Please contact support.");
		} catch (BadCredentialsException bce) {
		    throw new UserNotFoundException("Invalid username or password." + bce.getMessage());
		} catch (DisabledException de) {
		    throw new UserNotFoundException("Account is disabled."+de.getMessage());
		} catch (org.springframework.security.core.AuthenticationException ae) {
		    throw new UsernameNotFoundException("Authentication failed: " + ae.getMessage());
		}
	}

	public Set<Role> generateRoles() {
		Role userRole = new Role("USER", "A regular user role");
		Set<Role> roles = new HashSet<>();
		roles.add(userRole);
		return roles;
	}
}
