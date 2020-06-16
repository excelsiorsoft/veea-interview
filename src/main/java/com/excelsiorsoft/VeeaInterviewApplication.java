package com.excelsiorsoft;

import static com.excelsiorsoft.FileMappings.fileToSchemaRegistry;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;

import io.vavr.Tuple2;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
public class VeeaInterviewApplication {
	
	@Autowired
	private CsvProcessor csvProcessor;
	
	@Autowired
	private PersonageRepository personageRepository;

	public static void main(String[] args) {
		SpringApplication.run(VeeaInterviewApplication.class, args);
	}
	
	protected static Integer getRandomInteger(int maximum, int minimum) {
		return new Integer(((int) (Math.random() * (maximum - minimum))) + minimum);
	}
	
	@Bean
	ApplicationRunner applicationRunner(GreetingRepository greetingRepository) {
		
		return args -> {
			/*
			 * greetingRepository.save(new Greeting("Hello")); greetingRepository.save(new
			 * Greeting("Hi"));
			 */
			Tuple2<String, Map<String, Object>> mappings = fileToSchemaRegistry.get(getRandomInteger(0, 4));
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(mappings._1);
			File tmpFile = File.createTempFile("data", ".dat");
            tmpFile.deleteOnExit();
            FileUtils.copyInputStreamToFile(inputStream, tmpFile);
			List<Personage> processedRecords = csvProcessor.process(tmpFile, mappings._2);
			log.info("To insert: {}", processedRecords);
			processedRecords.forEach(record -> personageRepository.save(record));
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
