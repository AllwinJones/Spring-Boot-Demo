package com.allwin.first.exception;

@SuppressWarnings("serial")
public class NoMatchesFoundException  extends Exception{
	public NoMatchesFoundException(String message){
		super(message);
	}
}
