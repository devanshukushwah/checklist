package com.checklist.exception;

/**
 * Exception class that represents an error when attempting to create
 * a user that already exists in the system. This class extends
 * {@link MyAppException}.
 */
public class UserAlreadyExists extends MyAppException {

    private static final long serialVersionUID = 5158019176142663885L;

    /**
     * Default constructor that creates a new instance of 
     * {@link UserAlreadyExists} with a default error message.
     */
    public UserAlreadyExists() {
        super("User already exists");
    }
    
    /**
     * Constructs a new instance of {@link UserAlreadyExists} with a
     * specified error message.
     * 
     * @param message the detail message for the exception
     */
    public UserAlreadyExists(String message) {
        super(message);
    }
}
