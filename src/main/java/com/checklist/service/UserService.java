package com.checklist.service;

import com.checklist.exception.DatabaseError;
import com.checklist.exception.UserAlreadyExists;
import com.checklist.exception.UserNotFound;
import com.checklist.exception.UserPasswordIncorrect;
import com.checklist.model.User;

/**
 * Service interface for user-related operations.
 * Provides methods to create a user and retrieve user information.
 */
public interface UserService {
    
    /**
     * Creates a new user in the system.
     *
     * @param user the user object containing the user's details
     * @return true if the user was successfully created, false otherwise
     * @throws DatabaseError if there is an issue with the database
     * @throws UserAlreadyExists if a user with the same email already exists
     */
    boolean create(User user) throws DatabaseError, UserAlreadyExists; 

    /**
     * Retrieves a user by their email and password.
     *
     * @param email the email of the user
     * @param password the password of the user
     * @return the user object if found
     * @throws DatabaseError if there is an issue with the database
     * @throws UserNotFound if no user exists with the provided email
     * @throws UserPasswordIncorrect if the password is incorrect
     */
    User getUser(String email, String password) throws DatabaseError, UserNotFound, UserPasswordIncorrect;
}
