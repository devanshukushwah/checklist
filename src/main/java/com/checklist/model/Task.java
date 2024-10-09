package com.checklist.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a task in the checklist application.
 * This class holds the details of a task including its identifier, title,
 * status, and timestamps for creation and modification.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    private int id;               // Unique identifier for the task
    private String title;         // Title of the task
    private boolean status;       // Status of the task (e.g., completed or failed)
    private Date createdDate;     // Date the task was created
    private Date changedDate;     // Date the task was last modified

    /**
     * Constructs a new Task with the specified title and status.
     *
     * @param title the title of the task
     * @param status the status of the task
     */
    public Task(String title, boolean status) {
        this.title = title;
        this.status = status;
    }
}
