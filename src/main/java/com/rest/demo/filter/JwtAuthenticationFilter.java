package com.rest.demo.filter;

import java.io.IOException;
import java.util.Iterator;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rest.demo.entity.User;
import com.rest.demo.exceptions.UserNotFoundException;
import com.rest.demo.service.JwtService;
import com.rest.demo.service.RedisTokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	private final RedisTokenService redisTokenService;

	public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService,
			RedisTokenService redisTokenService) {
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
		this.redisTokenService = redisTokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		Iterator<String> it = request.getHeaderNames().asIterator();

		while (it.hasNext()) {
			System.out.println(it.next());
		}

		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {

			filterChain.doFilter(request, response);
			return;
		}

		String token = authHeader.substring(7);
		String username = jwtService.extractUsername(token);

		if (request.getServletPath().equals("/auth/logout")) {
			// Extract the token from the request
			if (authHeader != null && authHeader.startsWith("Bearer ")) {
				// remove the token in Redis
				redisTokenService.deleteToken(jwtService.extractUsername(token));
			}
			// Continue with the filter chain
			filterChain.doFilter(request, response);
			return;
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			User userDetails = (User) userDetailsService.loadUserByUsername(username);
			if (jwtService.isValid(token, userDetails)) {

				try {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());

					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(authToken);
				} catch (UserNotFoundException e) {
					throw new UserNotFoundException(e.getMessage());
				} catch (Exception e) {
					throw new UserNotFoundException(e.getMessage());
				}
			}
		}
		filterChain.doFilter(request, response);
	}
}
