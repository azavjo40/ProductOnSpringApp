package com.example.product.user.dto;

import com.example.product.user.ERole;
import com.example.product.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class UserPostDto {
	@NotEmpty
	@Size(min = 2, message = "name should have at least 2 characters")
	private String name;

	@NotEmpty(message = "Email should not be empty")
	@Email
	private String email;

	@NotNull(message = "Date of birth should not be null")
	private LocalDate dob;

	@Size(min = 6, message = "Password should have at least 6 characters")
	private String password;

	private List<ERole> roles;

	public UserPostDto() {
		super();
	}

	public UserPostDto(String name, String email, LocalDate dob, String password) {
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.password = password;
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

	public List<ERole> getRoles() {
		return roles;
	}

	public void setRoles(List<ERole> roles) {
		this.roles = roles;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return new User(this.name, this.email, this.dob, this.password, this.roles);
	}
}
