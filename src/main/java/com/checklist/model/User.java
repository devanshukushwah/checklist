package com.checklist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private int id;
	private String fullName;
	private String email;
	private String password;
	private String confirmPassword;
}
