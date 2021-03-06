/**
 * 
 */
package com.excelsiorsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.excelsiorsoft.domain.Places;
import com.excelsiorsoft.service.PlacesService;

import lombok.extern.log4j.Log4j2;

/**
 * @author Simeon
 *
 */
@Log4j2
@RestController
public class PlacesController {

	@Autowired
	private  PlacesService placesService;
	
		
	@GetMapping("/places")
	ResponseEntity<Places> externalPlaces(@RequestParam("lat") String lat, @RequestParam("lon") String lon){
		String lattitude = lat;
		String longitude = lon;
		return ResponseEntity.ok(placesService.obtainVenues(lattitude, longitude));
	}
	

	

	

}
