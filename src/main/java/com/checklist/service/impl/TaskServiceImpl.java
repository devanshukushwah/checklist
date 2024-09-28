package com.checklist.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.checklist.DAO.TaskDAO;
import com.checklist.model.Task;
import com.checklist.service.TaskService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
	
	private TaskDAO taskDAO;

	@Override
	public List<Task> getHomeTask() {
		return taskDAO.getHomeTask();
	}

}
