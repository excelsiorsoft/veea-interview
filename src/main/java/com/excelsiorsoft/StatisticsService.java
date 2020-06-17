/**
 * 
 */
package com.excelsiorsoft;

import static io.micrometer.core.instrument.util.StringUtils.isBlank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import io.micrometer.core.instrument.util.StringUtils;
import io.vavr.Tuple2;

/**
 * @author Simeon
 *
 */
@Service
public class StatisticsService {
	
	@Autowired
	private PersonageRepository personageRepository;
	
	/**
	 * @param input
	 * @return
	 */
	public Map<String, StatisticsBucket> buildStats(List<Personage> input, boolean ext) {
		final List<Personage> subjects = (CollectionUtils.isEmpty(input))
				? (List<Personage>) personageRepository.findAll()
				: input;
		final Map<String, StatisticsBucket> result = new HashMap<>();
		for (Personage personage : subjects) {
			processRecord(result, personage, ext);
		}
		return result;

	}
	
	private void processRecord(final Map<String,StatisticsBucket> context, final Personage record, boolean ext) {
		
		String color = record.getColor();
		String fullName = isBlank(record.getFullName())?new StringBuilder(record.getFirstName()).append(" ").append(record.getLastName()).toString():record.getFullName();
		if(context.containsKey(color)){
			if(ext) {
				StatisticsBucket bucket = context.get(color);
				int count = bucket.getCount();
				List<String> names = bucket.getNames();
				bucket.setCount(count+1);
				names.add(fullName);
				context.put(color, bucket);
			}else {
				StatisticsBucket bucket = context.get(color);
				int count = bucket.getCount();
				//List<String> names = bucket.getNames();
				bucket.setCount(count+1);
				//names.add(fullName);
				context.put(color, bucket);
			}
		}else {
			if(ext) {
				StatisticsBucket bucket = new StatisticsBucket();
				bucket.setColor(color);
				bucket.setCount(1);
				List<String> names = new ArrayList<>();
				names.add(fullName);
				bucket.setNames(names);
				context.put(color, bucket);
			}else {
				StatisticsBucket bucket = new StatisticsBucket();
				bucket.setColor(color);
				bucket.setCount(1);
				//List<String> names = new ArrayList<>();
				//names.add(fullName);
				//bucket.setNames(names);
				context.put(color, bucket);
			}
		}
	}

}
