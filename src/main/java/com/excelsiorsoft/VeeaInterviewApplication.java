package com.excelsiorsoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.excelsiorsoft.service.PersonageService;

@SpringBootApplication
public class VeeaInterviewApplication {
	
	
	@Autowired
	private PersonageService personageService;

	public static void main(String[] args) {
		SpringApplication.run(VeeaInterviewApplication.class, args);
	}
	
	
	
	@Bean
	ApplicationRunner applicationRunner(PersonageService personageService) {
		
		return args -> {
			personageService.saveAllPersonages();
		};
	}
	
}

