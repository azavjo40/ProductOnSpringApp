package com.example.product.user;

import com.example.product.user.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<UserResponseDto> getUser(@AuthenticationPrincipal User userLocal) {
		return userService.getUser(userLocal.getEmail());
	}

	@DeleteMapping
	public ResponseEntity<String> deleteUser(@AuthenticationPrincipal User userLocal) {
		return userService.deleteUser(userLocal.getId());
	}

	@PutMapping
	public ResponseEntity<String> updateUser(
			@AuthenticationPrincipal User userLocal,
			@RequestBody User user
	) {
		return userService.updateUser(userLocal.getId(), user);
	}

	@GetMapping(path = "admin")
	public ResponseEntity<String> getTest() {
		System.out.println("Test Roles");
		return ResponseEntity.ok("Test Roles");
	}
}