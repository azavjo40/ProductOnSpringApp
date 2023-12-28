package com.example.app.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public interface UserRepository
		extends JpaRepository<User, Long> {
	//SELECT * FROM User WHERE email = ?
	@Query("SELECT s FROM User s WHERE s.email = ?1")
	Optional<User> findUserByEmail(String email);
}
