package com.checklist.DAO;

import java.util.List;

import com.checklist.model.Task;

public interface TaskDAO {
	List<Task> getHomeTask();
	boolean updateStatus(int id, boolean status);
	boolean addTask(Task task);
}
