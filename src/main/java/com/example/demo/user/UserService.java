package com.example.demo.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User getUser(String email) {
		return getUserByEmail(email);
	}

	public ResponseEntity<String> register(User user) {
		Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
		if (userOptional.isPresent()) {
			throw new IllegalStateException("Email taken");
		}
		userRepository.save(user);
		return new ResponseEntity<String>("User created successfully", HttpStatus.OK);
	}

	public ResponseEntity<String> deleteUser(Long userId) {
		boolean exists = userRepository.existsById(userId);
		if (!exists) {
			throw new IllegalStateException("User with id " + userId + " does not exists");
		}
		userRepository.deleteById(userId);
		return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<String> updateUser(Long userId, User user) {
		User userRespos = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User with id " + userId + " does not exists"));
		String name = user.getName();
		String email = user.getEmail();
		LocalDate dob = user.getDob();

		if (name != null && !Objects.equals(userRespos.getName(), name)) {
			userRespos.setName(name);
		}

		if (email != null && !Objects.equals(userRespos.getEmail(), email)) {
			userRespos.setEmail(email);
		}

		if (dob != null && !Objects.equals(userRespos.getDob(), email)) {
			userRespos.setDob(dob);
		}
		return new ResponseEntity<String>("User updated successfully", HttpStatus.OK);
	}

	public User getUserByEmail(String email) {
		return email != null ? userRepository.findUserByEmail(email).orElse(null) : null;
	}
}
