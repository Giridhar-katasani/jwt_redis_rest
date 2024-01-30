package com.rest.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "http://localhost:9090", methods = {RequestMethod.GET, RequestMethod.POST})
@SpringBootApplication
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class SpringCoreDemoMaven1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCoreDemoMaven1Application.class, args);
	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//	    http.csrf().disable()
//	        .authorizeRequests()
//	        .antMatchers(HttpMethod.GET, "/example").permitAll() // Allow GET without authentication
//	        .antMatchers(HttpMethod.POST, "/example").permitAll() // Allow POST without authentication
//	        .anyRequest().authenticated()
//	        .and().httpBasic();
//	}
}
