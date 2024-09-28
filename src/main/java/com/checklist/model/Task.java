package com.checklist.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
	private int id;
	private String title;
	private boolean status;
	
	public Task(String title, boolean status) {
		this.title = title;
		this.status = status;
	}
}
