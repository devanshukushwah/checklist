package com.checklist.service;

import java.util.List;

import com.checklist.model.Task;
import com.checklist.model.TaskHistory;
import com.checklist.model.TaskSearchFilter;

/**
 * Service interface for managing tasks in the checklist application.
 * Provides methods for task-related operations, including retrieval, 
 * updating, and adding tasks.
 */
public interface TaskService {
    
    /**
     * Retrieves the list of tasks for the specified user for the home page.
     *
     * @param userId the ID of the user whose tasks are to be retrieved
     * @return a list of tasks for the user
     */
    List<Task> getHomeTask(int userId);
    
    /**
     * Updates the status of a task identified by its ID.
     *
     * @param id the ID of the task to be updated
     * @param status the new status of the task
     * @return true if the status was successfully updated, false otherwise
     */
    boolean updateStatus(int id, boolean status);
    
    /**
     * Adds a new task created by a specified user.
     *
     * @param createdBy the ID of the user creating the task
     * @param task the task to be added
     * @return true if the task was successfully added, false otherwise
     */
    boolean addTask(int createdBy, Task task);
    
    /**
     * Retrieves tasks based on the provided search filter for the specified user.
     *
     * @param taskSearchFilter the filter criteria for searching tasks
     * @param userId the ID of the user whose tasks are to be retrieved
     * @return a list of tasks matching the search criteria
     */
    List<Task> getTask(TaskSearchFilter taskSearchFilter, int userId);
    
    /**
     * Retrieves the task history for the specified user.
     *
     * @param userId the ID of the user whose task history is to be retrieved
     * @return a list of task history records for the user
     */
    List<TaskHistory> getTaskHistory(int userId);
}
