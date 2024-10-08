package com.checklist.DAO;

import com.checklist.exception.DatabaseError;
import com.checklist.exception.UserNotFound;
import com.checklist.model.User;


public interface UserDAO {
	int create(User user) throws DatabaseError;
	User getUser(String email, String password) throws DatabaseError, UserNotFound;
	boolean userExists(String email) throws DatabaseError;
}
