/**
 * 
 */
package com.excelsiorsoft;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		personageService.deleteAllPersonages();
		try {
			personageService.saveAllPersonages();
		} catch (Exception e) {
			log.error("Something happened during persisting...{}  Talk to admin.",e);
		}
		return readPersonages();
	}
	
	@PostMapping("/personages/personage")
	public ResponseEntity<Personage> addPersonage(@RequestBody Personage personage){
		ResponseEntity<Personage> result = null;
		personageService.deleteAllPersonages();
		Personage newPersonage = personageService.save(personage);
        try {
			result = ResponseEntity.created(new URI("/personages/personage/" + newPersonage.getId()))
			        .body(personage);
		} catch (URISyntaxException e) {
			log.error("Something happened during persisting...{}  Talk to admin.",e);
		}
        return result;
	}
	

}
