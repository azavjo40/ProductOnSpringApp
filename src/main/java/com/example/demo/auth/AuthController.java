package com.example.demo.auth;

import com.example.demo.auth.jwt.JwtTokenService;
import com.example.demo.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/auth")
public class AuthController {
	private final JwtTokenService jwtTokenService;

	public AuthController(JwtTokenService jwtTokenService) {
		this.jwtTokenService = jwtTokenService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User credentials) {
		String token = jwtTokenService.generateToken(credentials.getEmail());
		return ResponseEntity.ok(token);
	}
}
