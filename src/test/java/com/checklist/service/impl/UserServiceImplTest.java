package com.checklist.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import com.checklist.exception.UserNotFound;
import com.checklist.exception.UserPasswordIncorrect;
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
	private static final String INVALID_EMAIL = "invalid@invalid.invalid";
	private static final String INVALID_PASSWORD = "InvalidPassword";

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
	@DisplayName("should throw UserAlreadyExists exception when user already exist")
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
	
	@Test
	@DisplayName("should return user when successfully login")
	void testGetUserForSuccessfullyLogin() throws DatabaseError, UserNotFound, UserPasswordIncorrect {
		// Given
		User user = new User();
		user.setEmail(VALID_EMAIL);
		user.setPassword(VALID_PASSWORD);
		
		// When
		when(userDAO.userExists(VALID_EMAIL)).thenReturn(true);
		when(userDAO.getUser(VALID_EMAIL, VALID_PASSWORD)).thenReturn(user);
		
		// Then
		User res = userServiceImpl.getUser(VALID_EMAIL, VALID_PASSWORD);
		assertNotNull(res);
		assertEquals(user.getEmail(), res.getEmail());	
	}
	
	@Test
	@DisplayName("should throw UserNotFound exception when user not exists")
	void testGetUserForUserNotFound() throws DatabaseError {
		// When
		when(userDAO.userExists(INVALID_EMAIL)).thenReturn(false);
		
		// Then
		assertThrows(UserNotFound.class, () -> {
			userServiceImpl.getUser(INVALID_EMAIL, INVALID_PASSWORD);
		});
	}
	
	@Test
	void testGetUserForPasswordIncorrect() throws DatabaseError, UserNotFound {
		// Given
		User user = new User();
		user.setEmail(VALID_EMAIL);
		user.setPassword(VALID_PASSWORD);
		
		// When
		when(userDAO.userExists(VALID_EMAIL)).thenReturn(true);
		
		// Then
		assertThrows(UserPasswordIncorrect.class, () -> {
			userServiceImpl.getUser(VALID_EMAIL, INVALID_PASSWORD);
		});
	}

}
