package com.checklist.DAO;

import com.checklist.exception.DatabaseError;
import com.checklist.exception.UserNotFound;
import com.checklist.model.User;

/**
 * Interface representing the Data Access Object (DAO) for managing {@link User} data.
 * Provides methods for creating a user, retrieving user information, and checking if
 * a user already exists in the database.
 */
public interface UserDAO {

    /**
     * Creates a new user in the database.
     *
     * @param user the {@link User} object containing user information to be saved
     * @return the ID of the created user
     * @throws DatabaseError if there is an error interacting with the database
     */
    int create(User user) throws DatabaseError;

    /**
     * Retrieves a user based on the provided email and password.
     *
     * @param email the user's email address
     * @param password the user's password
     * @return the {@link User} object if the user is found
     * @throws DatabaseError if there is an error interacting with the database
     * @throws UserNotFound if no user is found with the provided email and password
     */
    User getUser(String email, String password) throws DatabaseError, UserNotFound;

    /**
     * Checks if a user with the given email already exists in the database.
     *
     * @param email the email address to check for existence
     * @return true if the user exists, false otherwise
     * @throws DatabaseError if there is an error interacting with the database
     */
    boolean userExists(String email) throws DatabaseError;
}
