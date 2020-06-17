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
	
	@Value("${foursquare.venues.query}")
	private String venuesQuery;
	
	@Value("${foursquare.clientId}")
	private String clientId;
	
	@Value("${foursquare.clientSecret}")
	private String clientSecret;
	
	@Value("${foursquare.version}")
	private String version;
	
	public Places obtainVenues() {
		RestTemplate restTemplate = new RestTemplate();
		String venuesSearchUrl = "https://api.foursquare.com/v2/venues/search?ll=40.7,-74&client_id= {clientId}&client_secret={clientSecret}&v={version}";
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
