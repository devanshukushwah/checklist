package com.checklist.exception;

/**
 * Exception class that represents an error when a user is not found
 * in the system. This class extends {@link MyAppException}.
 */
public class UserNotFound extends MyAppException {
    
    private static final long serialVersionUID = -8399368169272688677L;

    /**
     * Default constructor that creates a new instance of 
     * {@link UserNotFound} with a default error message.
     */
    public UserNotFound() {
        super("User not found");
    }
    
    /**
     * Constructs a new instance of {@link UserNotFound} with a
     * specified error message.
     * 
     * @param message the detail message for the exception
     */
    public UserNotFound(String message) {
        super(message);
    }
}
