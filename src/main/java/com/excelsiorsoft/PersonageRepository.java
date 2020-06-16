/**
 * 
 */
package com.excelsiorsoft;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Simeon
 *
 */
@Repository
public interface PersonageRepository extends CrudRepository<Personage, Long> {

}
