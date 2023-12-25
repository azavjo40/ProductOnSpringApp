package com.example.demo.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.util.Calendar.MARCH;

@Configuration
public class UserConfig {
	@Bean
	CommandLineRunner commandLineRunner(UserRepository repository) {
		return args -> {
			User admin = new User(
					"Admin",
					"admin@gmail.com",
					LocalDate.of(1992, MARCH, 25),
					"Test123!!!"
			);
			repository.saveAll(
					List.of(admin)
			);

		};
	}

	;
}
