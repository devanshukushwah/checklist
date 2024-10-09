package com.checklist.service.impl;

import org.springframework.stereotype.Service;

import com.checklist.DAO.UserDAO;
import com.checklist.exception.DatabaseError;
import com.checklist.exception.UserAlreadyExists;
import com.checklist.exception.UserNotFound;
import com.checklist.exception.UserPasswordIncorrect;
import com.checklist.model.User;
import com.checklist.service.UserService;

import lombok.AllArgsConstructor;

/**
 * Implementation of the UserService interface.
 * Provides methods for user-related operations, including user creation and retrieval.
 */
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    /**
     * Creates a new user in the system.
     *
     * @param user the user object containing the user's details
     * @return true if the user was successfully created, false otherwise
     * @throws DatabaseError if there is an issue with the database
     * @throws UserAlreadyExists if a user with the same email already exists
     */
    @Override
    public boolean create(User user) throws DatabaseError, UserAlreadyExists {
        // Check if the user already exists
        boolean existingUser = userDAO.userExists(user.getEmail());
        if (existingUser) {
            throw new UserAlreadyExists();
        }

        // Create the user if they do not exist
        return userDAO.create(user) > 0;
    }

    /**
     * Retrieves a user by email and password.
     *
     * @param email the email of the user
     * @param password the password of the user
     * @return the user object if found
     * @throws DatabaseError if there is an issue with the database
     * @throws UserNotFound if no user exists with the provided email
     * @throws UserPasswordIncorrect if the password is incorrect
     */
    @Override
    public User getUser(String email, String password) throws DatabaseError, UserNotFound, UserPasswordIncorrect {
        // Check if the user exists
        boolean existingUser = userDAO.userExists(email);
        if (!existingUser) {
            throw new UserNotFound();
        }

        // Fetch the user
        User user = userDAO.getUser(email, password);

        // Check if the user was found; if not, throw an exception for incorrect password
        if (user == null) {
            throw new UserPasswordIncorrect();
        }

        // Return the found user
        return user;
    }
}
