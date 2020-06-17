/**
 * 
 */
package com.excelsiorsoft;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.excelsiorsoft.Places.PlacesBuilder;
import com.jayway.jsonpath.JsonPath;

import lombok.extern.log4j.Log4j2;

/**
 * @author Simeon
 *
 */
@Log4j2
@Service
public class PlacesService {
	
	@Value("foursquare.venues.query")
	private String venuesQuery;
	
	public Places obtainVenues() {
		RestTemplate restTemplate = new RestTemplate();
		String venuesSearchUrl = "https://api.foursquare.com/v2/venues/search?ll=40.7,-74&client_id=4VMTK3PJUNCYS2WBR3LHOUIZOVULPA4QI4LQZD0CBV24VF3J&client_secret=B0DUHZAWTG4FKQGQLUGWI2FCXZUT1HF3CPFHM3TU5LLPZB4L&v=20200616";
		ResponseEntity<String> response = restTemplate.getForEntity(venuesSearchUrl, String.class);
		
		log.debug("FourSquare response body: {}", response.getBody());
		
		String json = response.getBody();

		List<String> venues = JsonPath.read(json, venuesQuery);	
		
		log.debug("JsonPath list of venues: {}", venues);
		
		Places result = new PlacesBuilder().places(venues).build();
		
		log.debug("Built Places dto: {}", result);
		
		return result;
		
	}
}
