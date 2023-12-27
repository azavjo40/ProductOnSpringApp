package com.example.product.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.List;

import static java.util.Calendar.MARCH;

@Configuration
public class UserConfig {
	private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserConfig(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository repository) {
		return args -> {
			User admin = new User(
					"Admin",
					"admin@gmail.com",
					LocalDate.of(1992, MARCH, 25),
					passwordEncoder.encode("Test123!!!"),
					List.of(ERole.ADMIN)
			);
			repository.saveAll(
					List.of(admin)
			);

		};
	}

	;
}
