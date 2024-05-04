package com.rest.demo.configuaration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rest.demo.filter.JwtAuthenticationFilter;
import com.rest.demo.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfigFilterChain {

	private final UserService userService;
	private final JwtAuthenticationFilter jwtAuthFilter;

	public SecurityConfigFilterChain(UserService userService, JwtAuthenticationFilter jwtAuthenticationFilter) {

		this.jwtAuthFilter = jwtAuthenticationFilter;
		this.userService = userService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				// Disable CSRF
				.csrf(csrf -> csrf.disable())

				// to Authorize requests
				.authorizeHttpRequests(req -> req.requestMatchers("/auth/**").permitAll().requestMatchers("/task/**")
						.hasAuthority("ROLE_user").requestMatchers("/user/**").hasAuthority("ROLE_Admin").anyRequest()
						.authenticated())
				// to Specify the UserDetailsService implementation
				.userDetailsService(userService)

				// to Configure session management as a stateless
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				// Correctly add the JWT filter before UsernamePasswordAuthenticationFilter
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

				// Exception handling for unauthorized and forbidden responses
				.exceptionHandling(e -> e
						.accessDeniedHandler((request, response, accessDeniedException) -> response
								.setStatus(HttpServletResponse.SC_FORBIDDEN))
						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));

		// Build and return the SecurityFilterChain
		return http.build();
	}
}
