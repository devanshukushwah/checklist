package com.checklist.exception;

/**
 * Exception class that represents an error when a user provides
 * an incorrect password during authentication. This class extends
 * {@link MyAppException}.
 */
public class UserPasswordIncorrect extends MyAppException {

    private static final long serialVersionUID = 5158019176142663885L;

    /**
     * Default constructor that creates a new instance of 
     * {@link UserPasswordIncorrect} with a default error message.
     */
    public UserPasswordIncorrect() {
        super("User password incorrect");
    }
    
    /**
     * Constructs a new instance of {@link UserPasswordIncorrect} with a
     * specified error message.
     * 
     * @param message the detail message for the exception
     */
    public UserPasswordIncorrect(String message) {
        super(message);
    }
}
