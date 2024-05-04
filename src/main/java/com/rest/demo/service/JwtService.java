package com.rest.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import io.jsonwebtoken.JwtParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.rest.demo.entity.User;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

@Service
public class JwtService {

	private final String secret_key = "4c74ec8fb399ffef37ea2532e885fed26fe77f7b92f0721b9c3bfaa7cae839fb";

	@Autowired
	private RedisTokenService redisTokenService;

	public JwtService() {
		System.out.println("from the jwt service");
	}

	public String extractUsername(String token) {
		System.out.println(token + "30");
		return extractClaim(token, Claims::getSubject);
	}

	public boolean isValid(String token, User user) {
		String username = extractUsername(token);

		String storedToken = redisTokenService.getToken(user.getUsername());

		if (storedToken != null && storedToken.equals(token)) {
			return (username.equals(user.getUsername())) && !isTokenExpired(token);
		}
		return false;
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> resolver) {
		Claims claims = extractAllClaims(token);
		System.out.println(claims + "56");
		return resolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {

		Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parseClaimsJws(token);
		return claimsJws.getBody();
	}

	public String generateToken(User user) {

		final String roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		String token = Jwts.builder().setSubject(user.getUsername()).claim("roles", roles)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)).signWith(getSigninKey())
				.compact();

		return token;
	}

	private SecretKey getSigninKey() {
		byte[] keyBytes = Decoders.BASE64URL.decode(secret_key);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
