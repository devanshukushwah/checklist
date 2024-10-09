package com.checklist.DAO;

import java.util.List;

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
     * Retrieves the task history for the specified user.
     *
     * @param userId the ID of the user whose task history is to be retrieved
     * @return a list of {@link TaskHistory} objects representing the user's task history
     */
    List<TaskHistory> getTaskHistory(int userId);
}
