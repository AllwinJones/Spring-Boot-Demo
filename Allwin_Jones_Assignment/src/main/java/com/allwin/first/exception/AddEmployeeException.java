package com.allwin.first.exception;

@SuppressWarnings("serial")
public class AddEmployeeException  extends Exception{
	public AddEmployeeException(String message){
		super(message);
	}
}