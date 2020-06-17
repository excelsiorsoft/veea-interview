/**
 * 
 */
package com.excelsiorsoft;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

/**
 * @author Simeon
 *
 */
@Log4j2
@RestController
public class StatisticsController {
	
	@Autowired
	private StatisticsService statisticsService;
	
	@GetMapping("/stats")
	public ResponseEntity<Collection<StatisticsBucket>> getStats(){
		return null;
		
	}
	
	@GetMapping("/stats/ext")
	public ResponseEntity<Collection<StatisticsBucket>> getStatsExt(){
		Map<String, StatisticsBucket> histogram = statisticsService.buildStats(null);
		return ResponseEntity.ok(histogram.values());
		
	}

}
