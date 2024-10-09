package com.checklist.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.checklist.DAO.TaskDAO;
import com.checklist.model.Task;
import com.checklist.model.TaskHistory;
import com.checklist.model.TaskSearchFilter;
import com.checklist.service.TaskService;

import lombok.AllArgsConstructor;

/**
 * Implementation of the TaskService interface for managing tasks in the checklist application.
 * This service layer interacts with the TaskDAO to perform CRUD operations on tasks.
 */
@AllArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    
    private TaskDAO taskDAO;

    /**
     * Retrieves the list of tasks for the specified user for the home page.
     *
     * @param userId the ID of the user whose tasks are to be retrieved
     * @return a list of tasks for the user
     */
    @Override
    public List<Task> getHomeTask(int userId) {
        return taskDAO.getHomeTask(userId);
    }

    /**
     * Updates the status of a task identified by its ID.
     *
     * @param id the ID of the task to be updated
     * @param status the new status of the task
     * @return true if the status was successfully updated, false otherwise
     */
    @Override
    public boolean updateStatus(int id, boolean status) {
        return taskDAO.updateStatus(id, status);
    }

    /**
     * Adds a new task created by a specified user.
     *
     * @param createdBy the ID of the user creating the task
     * @param task the task to be added
     * @return true if the task was successfully added, false otherwise
     */
    @Override
    public boolean addTask(int createdBy, Task task) {
        return taskDAO.addTask(createdBy, task);
    }
    
    /**
     * Retrieves tasks based on the provided search filter for the specified user.
     *
     * @param taskSearchFilter the filter criteria for searching tasks
     * @param userId the ID of the user whose tasks are to be retrieved
     * @return a list of tasks matching the search criteria
     */
    @Override
    public List<Task> getTask(TaskSearchFilter taskSearchFilter, int userId) {
        return taskDAO.getTask(taskSearchFilter, userId);
    }

    /**
     * Retrieves the task history for the specified user.
     *
     * @param userId the ID of the user whose task history is to be retrieved
     * @return a list of task history records for the user
     */
    @Override
    public List<TaskHistory> getTaskHistory(int userId) {
        return taskDAO.getTaskHistory(userId);
    }
}
