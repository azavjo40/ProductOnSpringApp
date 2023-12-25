package com.example.demo.user;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "app_user")
public class User {

	@Id
	@SequenceGenerator(
			name = "user_sequence",
			sequenceName = "user_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "user_sequence"
	)

	private Long id;
	private String name;
	private String email;
	private LocalDate dob;
	@Transient
	private Integer age;
	private String password;

	public User() {
	}

	public User(Long id, String name, String email, LocalDate dob, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.password = password;
	}

	public User(String name, String email, LocalDate dob, String password) {
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.password = password;
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
		return Period.between(this.dob, LocalDate.now()).getYears();
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", dob=" + dob +
				", age=" + age +
				'}';
	}
}
