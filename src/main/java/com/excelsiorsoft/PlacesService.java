/**
 * 
 */
package com.excelsiorsoft;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j2;

/**
 * @author Simeon
 *
 */
@Log4j2
@Service
public class PlacesService {
	
	
	public Places obtainVenues() {
		RestTemplate restTemplate = new RestTemplate();
		String venuesSearchUrl = "https://api.foursquare.com/v2/venues/search?ll=40.7,-74&client_id=4VMTK3PJUNCYS2WBR3LHOUIZOVULPA4QI4LQZD0CBV24VF3J&client_secret=B0DUHZAWTG4FKQGQLUGWI2FCXZUT1HF3CPFHM3TU5LLPZB4L&v=20200616";
		ResponseEntity<String> response = restTemplate.getForEntity(venuesSearchUrl, String.class);
		
		log.info("FourSquare response: {}", response);
		//List<String> items = Arrays.asList(str.split("\\s*,\\s*"));
		return null;
	}
}
