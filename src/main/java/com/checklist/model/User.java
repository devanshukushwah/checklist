package com.checklist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a user in the checklist application.
 * This class holds the user's personal information and credentials.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;                 // The unique identifier for the user
    private String fullName;        // The full name of the user
    private String email;           // The email address of the user
    private String password;        // The user's password for authentication
    private String confirmPassword; // The confirmation of the user's password
}
