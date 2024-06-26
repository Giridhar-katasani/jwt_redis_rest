package com.rest.demo.entity;

import java.io.Serializable;

public class JWTrequest implements Serializable {

	@Override
	public String toString() {
		return "JWTrequest [username=" + username + ", password=" + password + "]";
	}

	private static final long serialVersionUID = 5926468583005150707L;

	private String username;
	private String password;

	// Default constructor for JSON Parsing
	public JWTrequest() {
		
	}

	public JWTrequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
