package com.example.demo.auth.jwt;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenService {
	@Value("${myApp.jwtSecretKey}")
	private String jwtSecretKey;

	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 час
				.signWith(SignatureAlgorithm.HS512, jwtSecretKey)
				.compact();
	}

	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser()
					.setSigningKey(jwtSecretKey)
					.parseClaimsJws(token);
			return !claims.getBody().getExpiration().before(new Date());
		} catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException |
		         IllegalArgumentException e) {
			return false;
		}
	}

	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public String getSubjectFromToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser()
					.setSigningKey(jwtSecretKey)
					.parseClaimsJws(token);
			return claims.getBody().getSubject();
		} catch (Exception e) {
			// Обработка ошибок, например, невалидный токен
			return null;
		}
	}
}
