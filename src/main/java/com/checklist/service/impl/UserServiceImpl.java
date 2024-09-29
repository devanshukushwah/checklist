package com.checklist.service.impl;

import org.springframework.stereotype.Service;

import com.checklist.DAO.UserDAO;
import com.checklist.model.User;
import com.checklist.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	
	@Override
	public boolean create(User user) {
		// user already exist
		User dbUser = userDAO.getUser(user.getEmail(), user.getPassword());
		if (dbUser != null) {
			return false;
		}
		
		// create if not exist
		return userDAO.create(user) > 0;
	}

	@Override
	public User getUser(String email, String password) {
		return userDAO.getUser(email, password);
	}

}
