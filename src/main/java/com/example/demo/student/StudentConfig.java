package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			Student supantha = new Student("Supantha", "supantha@gmail.com", LocalDate.of(2001, Month.JULY, 2));
			Student ayushi = new Student("Ayushi", "ayushi@gmail.com", LocalDate.of(2001, Month.APRIL, 8));

			studentRepository.saveAll(
				List.of(supantha, ayushi)
			);
		};
	}
}
