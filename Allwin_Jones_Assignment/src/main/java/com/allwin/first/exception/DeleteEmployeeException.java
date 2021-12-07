package com.allwin.first.exception;

@SuppressWarnings("serial")
public class DeleteEmployeeException  extends Exception{
	public DeleteEmployeeException(String message){
		super(message);
	}
}
