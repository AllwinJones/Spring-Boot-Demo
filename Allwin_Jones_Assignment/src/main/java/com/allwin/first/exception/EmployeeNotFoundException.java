package com.allwin.first.exception;

@SuppressWarnings("serial")
public class EmployeeNotFoundException  extends Exception{
	public EmployeeNotFoundException(String message){
		super(message);
	}
}
