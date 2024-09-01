package com.cg.movierentalapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.cg.movierentalapp.repositories")
public class FilmRentalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmRentalServiceApplication.class, args);
	}

}

