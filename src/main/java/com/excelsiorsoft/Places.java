/**
 * 
 */
package com.excelsiorsoft;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Simeon
 *
 */
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@Builder
public class Places {

	private List<String> places;
}
