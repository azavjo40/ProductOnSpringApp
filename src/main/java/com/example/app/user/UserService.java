package com.example.app.user;

import com.example.app.auth.dto.LoginDto;
import com.example.app.common.jwt.JwtTokenService;
import com.example.app.user.dto.UserResponseDto;
import com.example.app.user.dto.UserResponseWithTokenDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final JwtTokenService jwtTokenService;

	@Autowired
	public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtTokenService jwtTokenService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenService = jwtTokenService;
	}

	public ResponseEntity<UserResponseDto> getUser(String email) {
		User user = getUserByEmail(email);
		return new ResponseEntity(user.getUser(), HttpStatus.OK);
	}

	public ResponseEntity<UserResponseWithTokenDto> register(User user, Boolean isAdmin) {
		Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
		if (userOptional.isPresent()) {
			throw new IllegalStateException("Email taken");
		}
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user.setRoles(List.of(isAdmin ? ERole.ROLE_ADMIN : ERole.ROLE_USER));
		User newUser = userRepository.save(user);

		String token = jwtTokenService.generateToken(newUser.getEmail(), newUser.getRoles());

		return new ResponseEntity(new UserResponseWithTokenDto(token, user.getUser()), HttpStatus.OK);
	}

	public ResponseEntity<UserResponseWithTokenDto> login(LoginDto userLogin) {
		User user = getUserByEmail(userLogin.getEmail());
		if (user == null) {
			return new ResponseEntity("User not found", HttpStatus.UNAUTHORIZED);
		}

		boolean matches = passwordEncoder.matches(userLogin.getPassword(), user.getPassword());
		if (!matches) {
			return new ResponseEntity("Invalid password", HttpStatus.UNAUTHORIZED);
		}

		String token = jwtTokenService.generateToken(userLogin.getEmail(), user.getRoles());

		return new ResponseEntity<>(new UserResponseWithTokenDto(token, user.getUser()), HttpStatus.OK);
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
