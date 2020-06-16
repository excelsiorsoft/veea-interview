package com.excelsiorsoft;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

import lombok.Data;

@SpringBootApplication
public class VeeaInterviewApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(VeeaInterviewApplication.class, args);
	}
	
	@Bean
	ApplicationRunner applicationRunner(GreetingRepository greetingRepository) {
		return args -> {
			greetingRepository.save(new Greeting("Hello"));
			greetingRepository.save(new Greeting("Hi"));
		};
	}
	
}

@Entity
@Data
class Greeting{
	
	public Greeting(String greeting) {
		this.message = greeting;
	}

	@Id
	@GeneratedValue
	private Long id;
	
	private String message;
	
}

interface GreetingRepository extends CrudRepository<Greeting, Long>{}
