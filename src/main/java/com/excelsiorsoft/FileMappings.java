/**
 * 
 */
package com.excelsiorsoft;

import java.util.LinkedHashMap;
import java.util.Map;

import org.supercsv.cellprocessor.Trim;
import org.supercsv.cellprocessor.constraint.StrRegEx;
import org.supercsv.cellprocessor.ift.CellProcessor;

import com.excelsiorsoft.CsvProcessor.SuppressException;

/**
 * @author Simeon
 *
 */
public final class FileMappings {
	
	public static final Map<String, Object> firstType = new LinkedHashMap<String, Object>(){{
		put("firstName", new SuppressException(new Trim(new StrRegEx("(\\w+)"))));
		put("lastName", new SuppressException(new Trim(new StrRegEx("(\\w+)"))));
		put("phoneNumber",new SuppressException(new Trim(new StrRegEx("(^\\d{3}(\\s{1}|-)\\d{3}(\\s{1}|-)\\d{4}$)"))));
		put("color",new SuppressException(new Trim()));
		put("zipCode", new SuppressException(new Trim(new StrRegEx("\\d{5}(-\\d{4})?"))));
	}};
	
	public static final Map<String, Object> secondType = new LinkedHashMap<String, Object>(){{
		put("firstName", new SuppressException(new Trim(new StrRegEx("(\\w+)"))));
		put("lastName", new SuppressException(new Trim(new StrRegEx("(\\w+)"))));
		put("color",new SuppressException(new Trim()));
		put("zipCode", new SuppressException(new Trim(new StrRegEx("\\d{5}(-\\d{4})?"))));
		put("phoneNumber",new SuppressException(new Trim(new StrRegEx("(^\\d{3}(\\s{1}|-)\\d{3}(\\s{1}|-)\\d{4}$)"))));
	}};
	
	public static final Map<String, Object> thirdType = new LinkedHashMap<String, Object>(){{
		put("firstName", new SuppressException(new Trim(new StrRegEx("(\\w+)"))));
		put("lastName", new SuppressException(new Trim(new StrRegEx("(\\w+)"))));
		put("zipCode", new SuppressException(new Trim(new StrRegEx("\\d{5}(-\\d{4})?"))));
		put("phoneNumber",new SuppressException(new Trim(new StrRegEx("(^\\d{3}(\\s{1}|-)\\d{3}(\\s{1}|-)\\d{4}$)"))));
		put("color",new SuppressException(new Trim()));
	}};
	
	public static final Map<String, Object> fourthType = new LinkedHashMap<String, Object>(){{
		put("fullName", new SuppressException(new Trim(new StrRegEx("(\\w+)\\s+(\\w+)"))));
		put("address", new SuppressException(new Trim(new StrRegEx("\\d+\\s+\\w+"))));
		put("zipCode", new SuppressException(new Trim(new StrRegEx("\\d{5}(-\\d{4})?"))));
		put("phoneNumber",new SuppressException(new Trim(new StrRegEx("(^\\d{3}(\\s{1}|-)\\d{3}(\\s{1}|-)\\d{4}$)"))));
		put("color",new SuppressException(new Trim()));
	}};

}
