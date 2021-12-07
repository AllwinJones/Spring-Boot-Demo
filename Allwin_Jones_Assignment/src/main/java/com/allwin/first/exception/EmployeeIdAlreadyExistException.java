package com.allwin.first.exception;

@SuppressWarnings("serial")
public class EmployeeIdAlreadyExistException  extends Exception{
	public EmployeeIdAlreadyExistException(String message){
		super(message);
	}
}
