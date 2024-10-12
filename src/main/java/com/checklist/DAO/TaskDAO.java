package com.checklist.DAO;

import java.util.List;

import com.checklist.model.PageRequest;
import com.checklist.model.PageResponse;
import com.checklist.model.Task;
import com.checklist.model.TaskHistory;
import com.checklist.model.TaskSearchFilter;

/**
 * DAO interface for managing tasks in the checklist application.
 * It provides methods for retrieving tasks, updating task status, adding new tasks, 
 * and fetching task history.
 */
public interface TaskDAO {

    /**
     * Retrieves a list of tasks for the home page for the specified user.
     *
     * @param userId the ID of the user whose tasks are to be retrieved
     * @return a list of {@link Task} objects for the user
     */
    List<Task> getHomeTask(int userId);

    /**
     * Updates the status of a task.
     *
     * @param id the ID of the task to be updated
     * @param status the new status of the task (true for complete, false for incomplete)
     * @return true if the update was successful, false otherwise
     */
    boolean updateStatus(int id, boolean status);

    /**
     * Adds a new task created by the specified user.
     *
     * @param createdBy the ID of the user creating the task
     * @param task the {@link Task} object containing the task details
     * @return true if the task was successfully added, false otherwise
     */
    boolean addTask(int createdBy, Task task);

    /**
     * Retrieves a list of tasks based on the specified search filter and user ID.
     *
     * @param taskSearchFilter the filter criteria for searching tasks
     * @param userId the ID of the user whose tasks are to be retrieved
     * @return a list of {@link Task} objects that match the filter criteria
     */
    List<Task> getTask(TaskSearchFilter taskSearchFilter, int userId);

    /**
     * Retrieves the paginated task history for a specific user.
     *
     * This method fetches task history records for the user with the given ID, 
     * applying pagination parameters such as page number and page size. The 
     * result includes a list of task history records and the total count of 
     * records available.
     *
     * The task history is fetched from a database view (`TASK_HISTORY_VIEW`) and 
     * ordered by the task's creation date in descending order. The pagination 
     * is achieved using SQL's `LIMIT` and `OFFSET` clauses based on the page 
     * request.
     *
     * @param userId the ID of the user for whom the task history is to be retrieved
     * @param pr the page request containing pagination parameters such as page number and page size
     * @return a {@link PageResponse} object containing a list of {@link TaskHistory} and the total count of records
     * 
     * @throws SQLException if a database access error occurs
     * @throws NumberFormatException if the pagination parameters are invalid
     */
    PageResponse<List<TaskHistory>> getTaskHistory(int userId, PageRequest pr);
}
