package com.checklist.DAO;

import java.util.List;

import com.checklist.model.Task;
import com.checklist.model.TaskHistory;
import com.checklist.model.TaskSearchFilter;

public interface TaskDAO {
	List<Task> getHomeTask(int userId);
	boolean updateStatus(int id, boolean status);
	boolean addTask(int createdBy, Task task);
	List<Task> getTask(TaskSearchFilter taskSearchFilter, int userId);
	List<TaskHistory> getTaskHistory(int userId);
}
