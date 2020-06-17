/**
 * 
 */
package com.excelsiorsoft;

import java.util.List;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Simeon
 *
 */
@Data
//@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class StatisticsBucket {
	
	private String color;
	private int count;
	private List<String> names;

}
