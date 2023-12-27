package com.example.product.auth;

import com.example.product.auth.dto.LoginDto;
import com.example.product.common.jwt.JwtTokenService;
import com.example.product.user.UserService;
import com.example.product.user.dto.UserPostDto;
import com.example.product.user.dto.UserResponseWithTokenDto;
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
	public ResponseEntity<UserResponseWithTokenDto> login(@RequestBody @Valid LoginDto user) {
		return userService.login(user);
	}

	@PostMapping("/register")
	public ResponseEntity<UserResponseWithTokenDto> register(@RequestBody @Valid UserPostDto user) {
		System.out.println(user);
		return userService.register(user.getUser(), false);
	}
}
