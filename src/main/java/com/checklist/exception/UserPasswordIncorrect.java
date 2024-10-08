package com.checklist.exception;

public class UserPasswordIncorrect extends MyAppException {

	private static final long serialVersionUID = 5158019176142663885L;

	public UserPasswordIncorrect() {
		super("User password incorrect");
	}
	
	public UserPasswordIncorrect(String message) {
		super(message);
	}
}
