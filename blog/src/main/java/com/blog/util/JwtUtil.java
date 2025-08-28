package com.blog.util;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
	private static final long EXPIRATION = 1000 * 60 * 60 * 24; // 24 hours

	private static final Key SECRET_KEY = Keys.hmacShaKeyFor("mySuperSecretKey1234567890!@#$%^".getBytes());

	public static String generateToken(int userId) {
		return Jwts.builder().setSubject(String.valueOf(userId)).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(SECRET_KEY, SignatureAlgorithm.HS256).compact();
	}

	public static Claims validateToken(String token) {
		return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
	}

	// Extract user ID from token
	public static int getUserId(String token) {
		Claims claims = validateToken(token);
		return Integer.parseInt(claims.getSubject());
	}
}
