/**
 * 
 */
package com.excelsiorsoft;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.supercsv.cellprocessor.Trim;
import org.supercsv.cellprocessor.constraint.StrRegEx;

import com.excelsiorsoft.service.CsvProcessor.SuppressException;

import io.vavr.Tuple;
import io.vavr.Tuple2;

/**
 * @author Simeon
 *
 */
public final class FileMappings {
	
	public static final Map<String, Object> firstSchema = new LinkedHashMap<String, Object>(){{
		put("firstName", new SuppressException(new Trim(new StrRegEx("(\\w+)"))));
		put("lastName", new SuppressException(new Trim(new StrRegEx("(\\w+)"))));
		put("phoneNumber",new SuppressException(new Trim(new StrRegEx("(^\\d{3}(\\s{1}|-)\\d{3}(\\s{1}|-)\\d{4}$)"))));
		put("color",new SuppressException(new Trim()));
		put("zipCode", new SuppressException(new Trim(new StrRegEx("\\d{5}(-\\d{4})?"))));
	}};
	
	public static final Map<String, Object> secondSchema = new LinkedHashMap<String, Object>(){{
		put("firstName", new SuppressException(new Trim(new StrRegEx("(\\w+)"))));
		put("lastName", new SuppressException(new Trim(new StrRegEx("(\\w+)"))));
		put("color",new SuppressException(new Trim()));
		put("zipCode", new SuppressException(new Trim(new StrRegEx("\\d{5}(-\\d{4})?"))));
		put("phoneNumber",new SuppressException(new Trim(new StrRegEx("(^\\d{3}(\\s{1}|-)\\d{3}(\\s{1}|-)\\d{4}$)"))));
	}};
	
	public static final Map<String, Object> thirdSchema = new LinkedHashMap<String, Object>(){{
		put("firstName", new SuppressException(new Trim(new StrRegEx("(\\w+)"))));
		put("lastName", new SuppressException(new Trim(new StrRegEx("(\\w+)"))));
		put("zipCode", new SuppressException(new Trim(new StrRegEx("\\d{5}(-\\d{4})?"))));
		put("phoneNumber",new SuppressException(new Trim(new StrRegEx("(^\\d{3}(\\s{1}|-)\\d{3}(\\s{1}|-)\\d{4}$)"))));
		put("color",new SuppressException(new Trim()));
	}};
	
	public static final Map<String, Object> fourthSchema = new LinkedHashMap<String, Object>(){{
		put("fullName", new SuppressException(new Trim(new StrRegEx("(\\w+)\\s+(\\w+)"))));
		put("address", new SuppressException(new Trim(new StrRegEx("\\d+\\s+\\w+"))));
		put("zipCode", new SuppressException(new Trim(new StrRegEx("\\d{5}(-\\d{4})?"))));
		put("phoneNumber",new SuppressException(new Trim(new StrRegEx("(^\\d{3}(\\s{1}|-)\\d{3}(\\s{1}|-)\\d{4}$)"))));
		put("color",new SuppressException(new Trim()));
	}};
	
	
	public static final Map<Integer, Tuple2<String, Map<String, Object>>> fileToSchemaRegistry = new HashMap<Integer, Tuple2<String, Map<String, Object>>>() {{
		put(new Integer(1), Tuple.of("1stRecordType", firstSchema));
		put(new Integer(2), Tuple.of("2ndRecordType", secondSchema));
		put(new Integer(3), Tuple.of("3rdRecordType", thirdSchema));
		put(new Integer(4), Tuple.of("4thRecordType", fourthSchema));
	}};
	
	

}
