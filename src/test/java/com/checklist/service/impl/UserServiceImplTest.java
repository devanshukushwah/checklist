package com.checklist.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.checklist.DAO.UserDAO;
import com.checklist.exception.DatabaseError;
import com.checklist.exception.UserAlreadyExists;
import com.checklist.model.User;

class UserServiceImplTest {
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	@Mock
	UserDAO userDAO;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	private static final int VALID_USER_ID = 1;
	private static final int INVALID_USER_ID = -1;
	private static final String VALID_EMAIL = "valid@valid.valid";
	private static final String VALID_PASSWORD = "ValidPassword";

	@Test
	@DisplayName("should create user when user not exist")
	void testCreateUser() throws DatabaseError, UserAlreadyExists {
		// Given
		User user = new User();
		user.setEmail(VALID_EMAIL);
		user.setPassword(VALID_PASSWORD);
		
		// When
		when(userDAO.userExists(VALID_EMAIL)).thenReturn(false);
		when(userDAO.create(user)).thenReturn(VALID_USER_ID);
		
		// Then
		boolean res = userServiceImpl.create(user);
		assertTrue(res);
	}
	
	@Test
	@DisplayName("should not create user when user already exist")
	void testNotCreateUser() throws DatabaseError, UserAlreadyExists {
		// Given
		User user = new User();
		user.setEmail(VALID_EMAIL);
		user.setPassword(VALID_PASSWORD);
		
		// When
		when(userDAO.userExists(VALID_EMAIL)).thenReturn(true);
		
		// Then
		assertThrows(UserAlreadyExists.class, () -> {
			userServiceImpl.create(user);
		});
	}

}
