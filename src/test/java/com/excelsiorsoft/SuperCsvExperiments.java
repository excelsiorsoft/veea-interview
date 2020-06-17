/**
 * 
 */
package com.excelsiorsoft;

import static com.excelsiorsoft.FileMappings.fileToSchemaRegistry;

import java.io.File;
import java.io.StringWriter;

/**
 * @author Simeon
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.StrMinMax;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

import com.excelsiorsoft.domain.Personage;
import com.excelsiorsoft.service.CsvProcessor;

import io.vavr.Tuple2;
import lombok.AllArgsConstructor;
import lombok.Data;

@SpringBootTest
public class SuperCsvExperiments {
	
	@Autowired
	private CsvProcessor csvProcessor;
	
	@Data
	@AllArgsConstructor
	public static class Person{
		String name;
		Integer age;
	}

	public static class SuppressException extends CellProcessorAdaptor {

		public static List<SuperCsvCellProcessorException> SUPPRESSED_EXCEPTIONS = new ArrayList<SuperCsvCellProcessorException>();

		public SuppressException(CellProcessor next) {
			super(next);
		}

		public Object execute(Object value, CsvContext context) {
			try {
				// attempt to execute the next processor
				return next.execute(value, context);

			} catch (SuperCsvCellProcessorException e) {
				// save the exception
				SUPPRESSED_EXCEPTIONS.add(e);

				// and suppress it (null is written as "")
				return null;
			}
		}
	}

	
	@Test
	public void supressExceptionsTest() throws Exception {
	      final CellProcessor[] PROCESSORS = {
	            new SuppressException(new StrMinMax(0, 4)),
	            new SuppressException(new NotNull()) };

	     final String[] HEADER = { "name", "age" };


	        final StringWriter stringWriter = new StringWriter();
	        ICsvBeanWriter beanWriter = null;
	        try {
	            beanWriter = new CsvBeanWriter(stringWriter,
	                    CsvPreference.STANDARD_PREFERENCE);

	            beanWriter.writeHeader(HEADER);

	            // set up the data
	            Person valid = new Person("Rick", 43);
	            Person nullAge = new Person("Lori", null);
	            Person totallyInvalid = new Person("Shane", null);
	            Person valid2 = new Person("Carl", 12);
	            List<Person> people = Arrays.asList(valid, nullAge, totallyInvalid,
	                    valid2);

	            for (Person person : people) {
	                beanWriter.write(person, HEADER, PROCESSORS);

	                if (!SuppressException.SUPPRESSED_EXCEPTIONS.isEmpty()) {
	                    System.out.println("Suppressed exceptions for row "
	                                        + beanWriter.getRowNumber() + ":");
	                    for (SuperCsvCellProcessorException e :
	                                        SuppressException.SUPPRESSED_EXCEPTIONS) {
	                        System.out.println(e);
	                    }
	                    // clear ready for next row
	                    SuppressException.SUPPRESSED_EXCEPTIONS.clear();
	                }

	            }

	        } finally {
	            beanWriter.close();
	        }

	        // CSV will have empty columns for invalid data
	        System.out.println(stringWriter);

	    //}

	}
	
	@Test
	public void read1stFileType() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		Tuple2<String, Map<String, Object>> mappings = fileToSchemaRegistry.get(Integer.valueOf(1));
		List<Personage> records = csvProcessor.process(new File(classLoader.getResource(mappings._1).getFile()), mappings._2);
		Assert.notEmpty(records,"Expect records");
	}
	
	@Test
	public void read2ndFileType() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		Tuple2<String, Map<String, Object>> mappings = fileToSchemaRegistry.get(Integer.valueOf(2));
		List<Personage> records = csvProcessor.process(new File(classLoader.getResource(mappings._1).getFile()), mappings._2);
		Assert.notEmpty(records,"Expect records");
	}
	
	@Test
	public void read3rdFileType() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		Tuple2<String, Map<String, Object>> mappings = fileToSchemaRegistry.get(Integer.valueOf(3));
		List<Personage> records = csvProcessor.process(new File(classLoader.getResource(mappings._1).getFile()), mappings._2);
		Assert.notEmpty(records,"Expect records");
	}
	
	@Test
	public void read4thFileType() throws Exception {
		Tuple2<String, Map<String, Object>> mappings = fileToSchemaRegistry.get(Integer.valueOf(4));
		List<Personage> records = csvProcessor.process(new File(getClass().getClassLoader().getResource(mappings._1).getFile()), mappings._2);
		Assert.notEmpty(records,"Expect records");
	}
}
