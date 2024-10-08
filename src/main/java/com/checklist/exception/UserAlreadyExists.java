package com.checklist.exception;

public class UserAlreadyExists extends MyAppException {

	private static final long serialVersionUID = 5158019176142663885L;

	public UserAlreadyExists() {
		super("User already exists");
	}
	
	public UserAlreadyExists(String message) {
		super(message);
	}
}
