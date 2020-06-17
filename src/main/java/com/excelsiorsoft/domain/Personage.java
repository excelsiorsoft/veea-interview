/**
 * 
 */
package com.excelsiorsoft.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonInclude(Include.NON_NULL)
public class Personage {
	
	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	private String fullName;
	private String address;
	private String zipCode;
	private String phoneNumber;
	private String color;

}
