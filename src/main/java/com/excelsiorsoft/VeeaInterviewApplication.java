package com.excelsiorsoft;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SpringBootApplication
public class VeeaInterviewApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(VeeaInterviewApplication.class, args);
	}
	
	protected static Integer getRandomInteger(int maximum, int minimum) {
		return new Integer(((int) (Math.random() * (maximum - minimum))) + minimum);
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
@NoArgsConstructor
@AllArgsConstructor
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
