package com.checklist.exception;

/**
 * Custom exception class that represents a database-related error
 * in the application. It extends from {@link MyAppException}.
 */
public class DatabaseError extends MyAppException {

    private static final long serialVersionUID = -8161229998705787724L;

    /**
     * Default constructor that creates a new instance of 
     * {@link DatabaseError} with a default error message.
     */
    public DatabaseError() {
        super("Database error");
    }
    
    /**
     * Constructs a new instance of {@link DatabaseError} with a
     * specified error message.
     * 
     * @param message the detail message for the exception
     */
    public DatabaseError(String message) {
        super(message);
    }
}
