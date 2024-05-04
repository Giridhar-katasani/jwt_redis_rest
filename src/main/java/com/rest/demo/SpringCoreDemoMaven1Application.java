package com.rest.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "http://localhost:9090", methods = { RequestMethod.GET, RequestMethod.POST })
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableTransactionManagement
public class SpringCoreDemoMaven1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCoreDemoMaven1Application.class, args);
	}
}
