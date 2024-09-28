package com.checklist.service;

import java.util.List;

import com.checklist.model.Task;

public interface TaskService {
	List<Task> getHomeTask();
	boolean updateStatus(int id, boolean status);
	boolean addTask(Task task);
}
