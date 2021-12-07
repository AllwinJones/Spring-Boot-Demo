package com.allwin.first.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Address {

	private Integer addressId;
	
	@NotEmpty(message="Employee ID should not be empty")
	private String empId;
	
	@Size(min=3, message="Street should be atleast 3 characters")
	private String street;
	
	@Size(min=3, message="City should be atleast 3 characters")
	private String city;
	
	@Size(min=3, message="District should be atleast 3 characters")
	private String district;
	
	@Size(min=3, message="State should be atleast 3 characters")
	private String state;
	
	@Size(min=6, max=6, message="Pincode should be 6 characters")
	private String pincode;
	
	private String lastUpdated;
	
}
