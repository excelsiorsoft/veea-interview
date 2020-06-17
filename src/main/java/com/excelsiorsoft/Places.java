/**
 * 
 */
package com.excelsiorsoft;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

/**
 * Representation of the vicinity venues obtain (as in this example from FourSquare) or elsewhere
 * 
 * @author Simeon
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@Builder
public class Places {

	private List<String> places;
}
