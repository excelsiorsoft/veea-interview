/**
 * 
 */
package com.excelsiorsoft;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

import lombok.extern.log4j.Log4j2;




/**
 * @author Simeon
 *
 */
@Log4j2
@Component
public class CsvProcessor {
	
	
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

	public List<Personage> process(final File filename, final Map<String, Object> schemaType) throws Exception {

		ICsvListReader listReader = null;
        try {
                listReader = new CsvListReader(new FileReader(filename), CsvPreference.STANDARD_PREFERENCE);
                
                final List<Personage> pp = new ArrayList<Personage>();
                while( (listReader.read()) != null ) {
                	
                	final CellProcessor[] processors =  schemaType.values().toArray(new CellProcessor[schemaType.values().size()]);
                	final List<Object> columnValues = listReader.executeProcessors(processors);
                	final Set<String> columnNames = schemaType.keySet();
                	
                	handleRecordErrors(listReader);
                	
                	buildRecords(pp, columnValues, columnNames);
                	
//				System.out.println(String.format("lineNo=%s, rowNo=%s, columns=%s, columnList=%s",
//						listReader.getLineNumber(), listReader.getRowNumber(), columnValues.size(), columnValues));
				
                }
                log.info("Records created {}: {}", pp.size(), pp);
                return pp;
                
        }finally {
            if( listReader != null ) {
                listReader.close();
        }
}
	}

	private  void buildRecords(List<Personage> pp, final List<Object> columnValues, final Set<String> columnNames) {
		final Personage p = new Personage();
		zip(columnNames, columnValues, (v1, v2) -> {
			
			try {
				PropertyUtils.setSimpleProperty(p, v1, v2);
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
			}
			
			
		});
		pp.add(p);
	}

	private void handleRecordErrors(final ICsvListReader listReader) {
		if (!SuppressException.SUPPRESSED_EXCEPTIONS.isEmpty()) {
			log.warn("\nSuppressed exceptions for row {}:", listReader.getRowNumber() );
		    for (SuperCsvCellProcessorException e : SuppressException.SUPPRESSED_EXCEPTIONS) {
		    	log.warn(e+"\n");
		    }
		    // clear ready for next row
		    SuppressException.SUPPRESSED_EXCEPTIONS.clear();
		}
	}
	
	private static <T,U> void zip(final Collection<T> ct, final Collection<U> cu, final BiConsumer<T, U> consumer) {
	    final Iterator<T> it = ct.iterator();
	    final Iterator<U> iu = cu.iterator();
	    while (it.hasNext() && iu.hasNext()) {
	        consumer.accept(it.next(), iu.next());
	    }
	}

}
