package com.checklist.exception;

public class DatabaseError extends MyAppException {

	private static final long serialVersionUID = -8161229998705787724L;

	public DatabaseError() {
		super("Database error");
	}
	
	public DatabaseError(String message) {
		super(message);
	}
}
