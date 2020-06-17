/**
 * 
 */
package com.excelsiorsoft.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excelsiorsoft.domain.StatisticsBucket;
import com.excelsiorsoft.service.StatisticsService;

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
		Map<String, StatisticsBucket> histogramExt = statisticsService.buildStats(null,false);
		return ResponseEntity.ok(histogramExt.values());
	}
	
	@GetMapping("/stats/ext")
	public ResponseEntity<Collection<StatisticsBucket>> getStatsExt(){
		Map<String, StatisticsBucket> histogramExt = statisticsService.buildStats(null,true);
		return ResponseEntity.ok(histogramExt.values());
		
	}

}
