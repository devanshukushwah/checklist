package com.checklist.exception;

/**
 * Base exception class for the application. This class extends
 * {@link Exception} and serves as a superclass for all custom 
 * exceptions specific to this application.
 */
public class MyAppException extends Exception {

    private static final long serialVersionUID = -8161229998705787724L;

    /**
     * Default constructor that creates a new instance of 
     * {@link MyAppException} with a default error message.
     */
    public MyAppException() {
        super("My app exception");
    }
    
    /**
     * Constructs a new instance of {@link MyAppException} with a
     * specified error message.
     * 
     * @param message the detail message for the exception
     */
    public MyAppException(String message) {
        super(message);
    }
}
