package com.checklist.DAO;

import com.checklist.model.User;


public interface UserDAO {
	int create(User user);
	User getUser(String email, String password);
}
