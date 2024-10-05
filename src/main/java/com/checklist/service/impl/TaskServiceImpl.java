package com.checklist.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.checklist.DAO.TaskDAO;
import com.checklist.model.Task;
import com.checklist.model.TaskSearchFilter;
import com.checklist.service.TaskService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
	
	private TaskDAO taskDAO;

	@Override
	public List<Task> getHomeTask(int userId) {
		return taskDAO.getHomeTask(userId);
	}

	@Override
	public boolean updateStatus(int id, boolean status) {
		return taskDAO.updateStatus(id, status);
	}

	@Override
	public boolean addTask(int createdBy, Task task) {
		return taskDAO.addTask(createdBy, task);
	}
	
	@Override
	public List<Task> getTask(TaskSearchFilter taskSearchFilter, int userId) {
		return taskDAO.getTask(taskSearchFilter, userId);
	}
	
	

}
