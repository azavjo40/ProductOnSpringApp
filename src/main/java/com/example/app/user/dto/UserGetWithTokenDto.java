package com.example.app.user.dto;

import com.example.app.user.entities.User;
import com.example.app.user.enums.ERole;

import java.time.LocalDate;
import java.util.List;

public class UserGetWithTokenDto {
	String token;
	private Long id;
	private String name;
	private String email;
	private LocalDate dob;
	private Integer age;
	private List<ERole> roles;

	public UserGetWithTokenDto(String token, User user) {
		this.token = token;
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.dob = user.getDob();
		this.age = user.getAge();
		this.roles = user.getRoles();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public List<ERole> getRoles() {
		return roles;
	}

	public void setRoles(List<ERole> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserGetWithTokenDto{" +
				"token='" + token + '\'' +
				", id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", dob=" + dob +
				", age=" + age +
				", roles=" + roles +
				'}';
	}
}
