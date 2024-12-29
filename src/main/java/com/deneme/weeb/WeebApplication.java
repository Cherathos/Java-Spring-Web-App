package com.deneme.weeb;

import com.deneme.weeb.furniture.JdbcClientFurnitureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WeebApplication {
	public static void main(String[] args) {
		SpringApplication.run(WeebApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(WeebApplication.class);

	@Bean
	CommandLineRunner carpenter(JdbcClientFurnitureRepository furnitureRepository) {
		return args -> {
		};
	}

}
