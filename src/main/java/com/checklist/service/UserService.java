package com.checklist.service;

import com.checklist.exception.DatabaseError;
import com.checklist.exception.UserAlreadyExists;
import com.checklist.exception.UserNotFound;
import com.checklist.exception.UserPasswordIncorrect;
import com.checklist.model.User;

public interface UserService {
	boolean create(User user) throws DatabaseError, UserAlreadyExists; 
	User getUser(String email, String password) throws DatabaseError, UserNotFound, UserPasswordIncorrect;
}
