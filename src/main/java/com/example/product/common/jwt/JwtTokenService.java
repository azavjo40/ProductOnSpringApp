package com.example.product.common.jwt;

import com.example.product.user.ERole;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JwtTokenService {
	@Value("${myApp.jwtSecretKey}")
	private String jwtSecretKey;

	public String generateToken(String username, List<ERole> roles) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 час
				.claim("roles", roles)
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

	public Claims getBodyOfToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser()
					.setSigningKey(jwtSecretKey)
					.parseClaimsJws(token);
			return claims.getBody();
		} catch (Exception e) {
			return null;
		}
	}
}
