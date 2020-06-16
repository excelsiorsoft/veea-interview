package com.excelsiorsoft;

import static com.excelsiorsoft.FileMappings.fileToSchemaRegistry;
import static com.excelsiorsoft.PersonageService.getRandomInteger;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class VeeaInterviewApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void getRandomInt() {
		for (int i = 0; i < 10; i++) {
			
			Integer rand = getRandomInteger(0, 4);
			String key = fileToSchemaRegistry.get(rand)._1();
			Map<String, Object> value = fileToSchemaRegistry.get(rand)._2();
			
			Assert.notNull(key, "Expect non-null key");
			Assert.notNull(value, "Expect non-null value");
			
			System.out.println("Number: " + rand + " mapping: " + key + "->"
					+ value);
		}
	}

}
