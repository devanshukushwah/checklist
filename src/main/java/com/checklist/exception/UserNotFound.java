package com.checklist.exception;

public class UserNotFound extends MyAppException {
	
	private static final long serialVersionUID = -8399368169272688677L;

	public UserNotFound() {
		super("User not found");
	}
	
	public UserNotFound(String message) {
		super(message);
	}
}
