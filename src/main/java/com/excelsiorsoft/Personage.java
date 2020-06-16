/**
 * 
 */
package com.excelsiorsoft;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
