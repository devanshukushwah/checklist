package com.checklist.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the history of tasks in the checklist application.
 * This class contains details about the task's completion status 
 * and the total records associated with a particular time period.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskHistory {
    private Date createdDate;     // The date when the task history record was created
    private int completed;         // The number of tasks completed on the created date
    private int totalRecords;      // The total number of tasks for the created date
}
