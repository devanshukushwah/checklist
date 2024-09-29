package com.checklist.DAO;

import java.util.List;

import com.checklist.model.Task;

public interface TaskDAO {
	List<Task> getHomeTask(int userId);
	boolean updateStatus(int id, boolean status);
	boolean addTask(int createdBy, Task task);
}
