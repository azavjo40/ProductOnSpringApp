package com.example.demo.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginDto {
	@NotEmpty(message = "Email should not be empty")
	@Email
	private String email;
	@Size(min = 6, message = "Password should have at least 6 characters")
	private String password;

	public LoginDto() {
		super();
	}

	public LoginDto(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
