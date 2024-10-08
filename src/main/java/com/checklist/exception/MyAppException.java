package com.checklist.exception;

public class MyAppException extends Exception {

	private static final long serialVersionUID = -8161229998705787724L;

	public MyAppException() {
		super("My app exception");
	}
	
	public MyAppException(String message) {
		super(message);
	}
}
