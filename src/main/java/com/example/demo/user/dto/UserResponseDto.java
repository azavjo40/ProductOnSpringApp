package com.example.demo.user.dto;

import com.example.demo.user.User;

public class UserResponseDto {
	String token;
	User user;

	public UserResponseDto(String token, User user) {
		user.setPassword("");
		this.token = token;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
