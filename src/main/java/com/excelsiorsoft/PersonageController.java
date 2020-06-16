/**
 * 
 */
package com.excelsiorsoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Simeon
 *
 */
@RestController
public class PersonageController {

	@Autowired
	private  PersonageRepository personageRepository;
	
		
	@GetMapping("/personages")
	Iterable<Personage> personages(){
		return personageRepository.findAll();
	}

}
