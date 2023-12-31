package com.example.app.user;

import com.example.app.common.dto.ResponseDto;
import com.example.app.user.dto.UserGetDto;
import com.example.app.user.entities.User;
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
	public ResponseEntity<ResponseDto<UserGetDto>> getUser(@AuthenticationPrincipal User userLocal) {
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
}
