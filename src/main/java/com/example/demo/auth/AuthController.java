package com.example.demo.auth;

import com.example.demo.common.jwt.JwtTokenService;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import com.example.demo.user.dto.UserPostDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/auth")
public class AuthController {
	private final JwtTokenService jwtTokenService;
	private final UserService userService;

	public AuthController(JwtTokenService jwtTokenService, UserService userService) {
		this.jwtTokenService = jwtTokenService;
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User credentials) {
		String token = jwtTokenService.generateToken(credentials.getEmail());
		return ResponseEntity.ok(token);
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody @Valid UserPostDto user) {
		System.out.println(user);
		return userService.register(user.getUser());
	}
}
