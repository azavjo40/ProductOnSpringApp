package com.example.app.user.dto;

import com.example.app.user.enums.ERole;

import java.time.LocalDate;
import java.util.List;

public class UserResponseDto {
	private Long id;
	private String name;
	private String email;
	private LocalDate dob;
	private Integer age;
	private List<ERole> roles;

	public UserResponseDto(Long id, String name, String email, LocalDate dob, Integer age, List<ERole> roles) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.age = age;
		this.roles = roles;
	}

	public List<ERole> getRoles() {
		return roles;
	}

	public void setRoles(List<ERole> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
