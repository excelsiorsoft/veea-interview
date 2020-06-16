/**
 * 
 */
package com.excelsiorsoft;

import static com.excelsiorsoft.FileMappings.fileToSchemaRegistry;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author Simeon
 *
 */
import org.springframework.stereotype.Service;

import io.vavr.Tuple2;
import lombok.extern.log4j.Log4j2;

/**
 * @author Simeon
 *
 */
@Log4j2
@Service
public class PersonageService {
    
    @Autowired
    private PersonageRepository personageRepository;
    
    @Autowired
	private CsvProcessor csvProcessor;
    
    
    public static Integer getRandomInteger(int maximum, int minimum) {
		return new Integer(((int) (Math.random() * (maximum - minimum))) + minimum);
	}
    
    /**
     * Save a full set of personages based on a randomly chosen file schema
     * @throws Exception 
     */
    public void saveAllPersonages() throws Exception {
    	Tuple2<String, Map<String, Object>> mappings = fileToSchemaRegistry.get(getRandomInteger(0, 4));
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(mappings._1);
		File tmpFile = File.createTempFile("data", ".dat");
        tmpFile.deleteOnExit();
        FileUtils.copyInputStreamToFile(inputStream, tmpFile);
		List<Personage> processedRecords = csvProcessor.process(tmpFile, mappings._2);
		log.info("To insert: {}", processedRecords);
		processedRecords.forEach(record -> personageRepository.save(record));
    }
    
    public void save(Personage personage) {
    	personageRepository.save(personage);
    }

}
