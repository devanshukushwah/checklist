package com.checklist.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a filter used for searching tasks in the checklist application.
 * This class encapsulates the criteria that can be used to filter tasks based on 
 * their creation date and status.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskSearchFilter {
    private Date createdFrom;  // The start date for filtering tasks
    private Date createdTo;    // The end date for filtering tasks
    private Boolean status;     // The status of the tasks (e.g., completed or not)
}
