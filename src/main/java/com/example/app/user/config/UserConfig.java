package com.example.app.user.config;

import com.example.app.user.entities.User;
import com.example.app.user.enums.ERole;
import com.example.app.user.repositories.UserRepository;
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
		List<User> usersBd = repository.findAll();
		return args -> {
			User admin = new User(
					"Admin",
					"admin@gmail.com",
					LocalDate.of(1992, MARCH, 25),
					passwordEncoder.encode("Test123!!!"),
					List.of(ERole.ROLE_ADMIN)
			);

			if (usersBd == null || usersBd.isEmpty()) {
				repository.saveAll(
						List.of(admin)
				);
			}
		};
	}

	;
}
