/**
 * 
 */
package com.excelsiorsoft;

import java.io.File;
import java.io.StringWriter;

/**
 * @author Simeon
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.StrMinMax;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

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

	    //public static void main(String[] args) throws Exception {

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
		csvProcessor.process(new File(classLoader.getResource("1stRecordType").getFile()), FileMappings.firstType);
	}
	
	@Test
	public void read2ndFileType() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		csvProcessor.process(new File(classLoader.getResource("2ndRecordType").getFile()), FileMappings.secondType);
	}
	
	@Test
	public void read3rdFileType() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		csvProcessor.process(new File(classLoader.getResource("3rdRecordType").getFile()), FileMappings.thirdType);
	}
	
	@Test
	public void read4thFileType() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		csvProcessor.process(new File(classLoader.getResource("4thRecordType").getFile()), FileMappings.fourthType);
	}
}
