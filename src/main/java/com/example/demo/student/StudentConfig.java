package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.util.Calendar.MARCH;

@Configuration
public class StudentConfig {
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			Student admin = new Student(
					"Admin",
					"admin@gmail.com",
					LocalDate.of(1992, MARCH, 25)
			);
			repository.saveAll(
					List.of(admin)
			);

		};
	}

	;
}
