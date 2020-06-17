/**
 * 
 */
package com.excelsiorsoft;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import lombok.extern.log4j.Log4j2;

/**
 * @author Simeon
 *
 */
@Log4j2
@Service
public class PlacesService {
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	public Places obtainVenues() {
		RestTemplate restTemplate = new RestTemplate();
		String venuesSearchUrl = "https://api.foursquare.com/v2/venues/search?ll=40.7,-74&client_id=4VMTK3PJUNCYS2WBR3LHOUIZOVULPA4QI4LQZD0CBV24VF3J&client_secret=B0DUHZAWTG4FKQGQLUGWI2FCXZUT1HF3CPFHM3TU5LLPZB4L&v=20200616";
		ResponseEntity<String> response = restTemplate.getForEntity(venuesSearchUrl, String.class);
		
		log.info("FourSquare response: {}", response);
		log.info("FourSquare response body: {}", response.getBody());
		
		String json = response.getBody();

		List<String> venues = JsonPath.read(json, "response.venues[*].name");	
		
		log.info("JsonPath list of venues: {}", venues);
		
		//List<String> items = Arrays.asList(str.split("\\s*,\\s*"));
		return null;
	}
}
