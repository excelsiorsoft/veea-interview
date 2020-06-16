/**
 * 
 */
package com.excelsiorsoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

/**
 * @author Simeon
 *
 */
@Log4j2
@RestController
public class PersonageController {

	@Autowired
	private  PersonageService personageService;
	
		
	@GetMapping("/personages")
	Iterable<Personage> readPersonages(){
		return personageService.showAllPersonages();
	}
	
	@PostMapping("/personages")
	Iterable<Personage> writePersonages(){
		try {
			personageService.saveAllPersonages();
		} catch (Exception e) {
			log.error("Something happened during persisting...{}  Talk to admin.",e);
		}
		return readPersonages();
	}

}
