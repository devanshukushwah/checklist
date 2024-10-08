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

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	
	@Override
	public boolean create(User user) throws DatabaseError, UserAlreadyExists {
		// user already exist
		boolean existingUser = userDAO.userExists(user.getEmail());
		if (existingUser == true) {
			throw new UserAlreadyExists();
		}
		
		// create if not exist
		return userDAO.create(user) > 0;
	}

	@Override
	public User getUser(String email, String password) throws DatabaseError, UserNotFound, UserPasswordIncorrect {
		// check user exist
		boolean existingUser = userDAO.userExists(email);
		if (existingUser == false) {
			throw new UserNotFound();
		}
		
		// fetch user
		User user = userDAO.getUser(email, password);
		
		// check user found if not password incorrect
		if(user == null) {
			throw new UserPasswordIncorrect();
		}
		
		// success
		return user;
	}

}
