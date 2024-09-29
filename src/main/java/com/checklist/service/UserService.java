package com.checklist.service;

import com.checklist.model.User;

public interface UserService {
	boolean create(User user); 
	User getUser(String email, String password);
}
