package com.example.demo.user.dto;

import com.example.demo.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UserPostDto {
	@NotEmpty
	@Size(min = 2, message = "name should have at least 2 characters")
	private String name;

	@NotEmpty(message = "Email should not be empty")
	@Email
	private String email;

	@NotNull(message = "Date of birth should not be null")
	private LocalDate dob;

	public UserPostDto() {
		super();
	}

	public UserPostDto(String name, String email, LocalDate dob) {
		this.name = name;
		this.email = email;
		this.dob = dob;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public User getUser() {
		return new User(this.name, this.email, this.dob);
	}
}
