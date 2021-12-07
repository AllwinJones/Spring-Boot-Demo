package com.allwin.first.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullEmployee {

	private String empId;
	private String empName;
	private String empGender;
	private String empEmail;
	private String empPhone;
	private String empPosition;
	private List<Address> address;
	
}
