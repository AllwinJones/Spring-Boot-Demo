package com.allwin.first.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Employee {

	@Id
	@NotEmpty(message="Employee ID should not be empty")
	private String empId;
	
	@Size(min=3, message="Name should be atleast 3 characters")
	private String empName;
	
	@Size(min=4, max=6, message="Gender should be 4-6 characters")
	private String empGender;
	
	@NotEmpty(message="Email should not be empty")
	@Email(message="Invalid Email format")
	private String empEmail;
	
	@Size(min=10, max=10, message="Phone number should be 10 characters")
	private String empPhone;
	
	@Size(min=4, message="Position should be atleast 4 characters")
	private String empPosition;
	
	private String lastUpdated;
	
}
