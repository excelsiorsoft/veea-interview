package com.excelsiorsoft;

import static com.excelsiorsoft.FileMappings.fileToSchemaRegistry;
import static com.excelsiorsoft.VeeaInterviewApplication.getRandomInteger;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VeeaInterviewApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void getRandomInt() {
		for (int i = 0; i < 10; i++) {
			Integer rand = getRandomInteger(0, 4);
			System.out.println("Number: " + rand + " mapping: " + fileToSchemaRegistry.get(rand)._1() + "->"
					+ fileToSchemaRegistry.get(rand)._2());
		}
	}

}
