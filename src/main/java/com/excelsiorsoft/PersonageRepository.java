/**
 * 
 */
package com.excelsiorsoft;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.excelsiorsoft.domain.Personage;

/**
 * @author Simeon
 *
 */
@Repository
public interface PersonageRepository extends CrudRepository<Personage, Long> {

}
