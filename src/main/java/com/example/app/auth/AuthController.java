package com.example.app.auth;

import com.example.app.auth.dto.LoginDto;
import com.example.app.common.dto.ResponseDto;
import com.example.app.common.jwt.JwtTokenService;
import com.example.app.user.UserService;
import com.example.app.user.dto.UserGetWithTokenDto;
import com.example.app.user.dto.UserPostDto;
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
	public ResponseEntity<ResponseDto<UserGetWithTokenDto>> login(@RequestBody @Valid LoginDto user) {
		return userService.login(user);
	}

	@PostMapping("/register")
	public ResponseEntity<ResponseDto<UserGetWithTokenDto>> register(@RequestBody @Valid UserPostDto user) {
		System.out.println(user);
		return userService.register(user.getUser(), false);
	}
}
