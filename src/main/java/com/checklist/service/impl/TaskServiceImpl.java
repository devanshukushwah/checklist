package com.checklist.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.checklist.DAO.TaskDAO;
import com.checklist.model.PageRequest;
import com.checklist.model.PageResponse;
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
     * {@inheritDoc}
     */
    @Override
    public List<Task> getHomeTask(int userId) {
        return taskDAO.getHomeTask(userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateStatus(int id, boolean status) {
        return taskDAO.updateStatus(id, status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addTask(int createdBy, Task task) {
        return taskDAO.addTask(createdBy, task);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Task> getTask(TaskSearchFilter taskSearchFilter, int userId) {
        return taskDAO.getTask(taskSearchFilter, userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PageResponse<List<TaskHistory>> getTaskHistory(int userId, PageRequest pr) {
        return taskDAO.getTaskHistory(userId, pr);
    }
}
