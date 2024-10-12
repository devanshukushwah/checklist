package com.checklist.service;

import java.util.List;

import com.checklist.model.PageRequest;
import com.checklist.model.PageResponse;
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
     * Retrieves the task history for the given user with pagination.
     *
     * This method fetches the task history for a specific user from the database 
     * based on the user's ID. It supports pagination and allows specifying the 
     * number of records to fetch per page and the current page number. If the 
     * pagination details are not provided, the method uses default values for 
     * page size and offset.
     *
     * The pagination logic works as follows:
     * - `LIMIT` specifies the maximum number of records to retrieve.
     * - `OFFSET` determines the starting point for the records (calculated as: `(pageNo - 1) * pageSize`).
     *
     * Example:
     * - Page 1: LIMIT 10, OFFSET 0
     * - Page 2: LIMIT 10, OFFSET 10
     * - Page 3: LIMIT 10, OFFSET 20
     * 
     * Sorting is applied based on the `CREATED_DATE` in descending order (newest tasks first).
     *
     * @param userId the ID of the user whose task history is to be retrieved.
     * @param pr the {@link PageRequest} object containing pagination details such as 
     *           page number, page size, and sorting column (if applicable).
     * @return a list of {@link TaskHistory} objects representing the user's task history, 
     *         paginated according to the specified parameters.
     */
    PageResponse<List<TaskHistory>> getTaskHistory(int userId, PageRequest pr);
}
