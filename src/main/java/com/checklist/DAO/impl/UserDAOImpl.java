package com.checklist.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Component;

import com.checklist.DAO.UserDAO;
import com.checklist.database.DBConnect;
import com.checklist.exception.DatabaseError;
import com.checklist.exception.UserNotFound;
import com.checklist.model.User;

import lombok.AllArgsConstructor;

/**
 * Implementation of the {@link UserDAO} interface for managing user data in the database.
 * This class provides methods for creating a user, retrieving user information based on 
 * email and password, and checking if a user exists in the database.
 */
@AllArgsConstructor
@Component
public class UserDAOImpl implements UserDAO {

    private DBConnect dbConnect;

    /**
     * Creates a new user in the database.
     *
     * @param user the {@link User} object containing user information to be saved
     * @return the number of rows affected in the database
     * @throws DatabaseError if there is an error interacting with the database
     */
    @Override
    public int create(User user) throws DatabaseError {
        try {
            Connection conn = dbConnect.getConn();
            String sql = "INSERT INTO \"USER\"(FULL_NAME, EMAIL, PASSWORD) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            int rows = ps.executeUpdate();
            return rows;
        } catch (Exception ex) {    
            throw new DatabaseError();
        }
    }

    /**
     * Retrieves a user based on the provided email and password.
     *
     * @param email the user's email address
     * @param password the user's password
     * @return the {@link User} object if the user is found
     * @throws DatabaseError if there is an error interacting with the database
     * @throws UserNotFound if no user is found with the provided email and password
     */
    @Override
    public User getUser(String email, String password) throws DatabaseError {
        try {
            Connection conn = dbConnect.getConn();
            String sql = "SELECT * FROM \"USER\" WHERE EMAIL = ? AND PASSWORD = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(email);
                user.setFullName(rs.getString("full_name"));
                return user;
            }
        } catch (Exception ex) {    
            throw new DatabaseError();
        }
        
        return null;
    }

    /**
     * Checks if a user with the given email already exists in the database.
     *
     * @param email the email address to check for existence
     * @return true if the user exists, false otherwise
     * @throws DatabaseError if there is an error interacting with the database
     */
    @Override
    public boolean userExists(String email) throws DatabaseError {
        try {
            Connection conn = dbConnect.getConn();
            String sql = "SELECT EXISTS (SELECT 1 FROM \"USER\" WHERE EMAIL = ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getBoolean(1);
            }
        } catch (Exception ex) {    
            throw new DatabaseError();
        }
        
        return false;
    }
}
