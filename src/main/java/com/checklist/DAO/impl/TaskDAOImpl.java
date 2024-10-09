package com.checklist.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.checklist.DAO.TaskDAO;
import com.checklist.database.DBConnect;
import com.checklist.model.Task;
import com.checklist.model.TaskHistory;
import com.checklist.model.TaskSearchFilter;

import lombok.AllArgsConstructor;

/**
 * Implementation of the {@link TaskDAO} interface for interacting with the task-related
 * data in the database. This class handles CRUD operations on tasks, task history, 
 * and task status updates.
 */
@AllArgsConstructor
@Component
public class TaskDAOImpl implements TaskDAO {

    private DBConnect dbConnect;

    /**
     * Retrieves the tasks for the home page for the given user.
     * The tasks fetched are those created by the user on the current day.
     *
     * @param userId the ID of the user whose tasks are to be retrieved
     * @return a list of {@link Task} objects representing the user's tasks for today
     */
    @Override
    public List<Task> getHomeTask(int userId) {
        List<Task> allTask = new ArrayList<>();
        try {
            Connection conn = dbConnect.getConn();

            String sql = "SELECT * FROM TASK WHERE CREATED_BY = ? AND created_date >= CURRENT_DATE AND created_date < CURRENT_DATE + INTERVAL '1 day' ORDER BY CREATED_DATE ASC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                Task task = Task.builder()
                        .id(result.getInt("id"))
                        .title(result.getString("title"))
                        .status(result.getBoolean("status"))
                        .build();
                allTask.add(task);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return allTask;
    }

    /**
     * Updates the status of the task with the given ID.
     *
     * @param id the ID of the task to be updated
     * @param status the new status of the task (true for complete, false for incomplete)
     * @return true if the update was successful, false otherwise
     */
    @Override
    public boolean updateStatus(int id, boolean status) {
        try {
            Connection conn = dbConnect.getConn();

            String sql = "UPDATE TASK SET STATUS = ?, CHANGED_DATE = CURRENT_TIMESTAMP WHERE ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /**
     * Adds a new task created by the specified user.
     *
     * @param createdBy the ID of the user creating the task
     * @param task the {@link Task} object containing task details
     * @return true if the task was successfully added, false otherwise
     */
    @Override
    public boolean addTask(int createdBy, Task task) {
        try {
            Connection conn = dbConnect.getConn();

            String sql = "INSERT INTO TASK(TITLE, STATUS, CREATED_BY) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, task.getTitle());
            ps.setInt(3, createdBy);
            ps.setBoolean(2, task.isStatus());
            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /**
     * Retrieves tasks based on the specified filter criteria and user ID.
     *
     * @param tsf the {@link TaskSearchFilter} object containing filter criteria
     * @param userId the ID of the user whose tasks are to be retrieved
     * @return a list of {@link Task} objects that match the filter criteria
     */
    @Override
    public List<Task> getTask(TaskSearchFilter tsf, int userId) {
        List<Task> allTask = new ArrayList<>();
        try {
            Connection conn = dbConnect.getConn();

            StringBuilder sql = new StringBuilder("SELECT * FROM TASK WHERE CREATED_BY = ?");

            if (tsf.getCreatedFrom() != null) {
                sql.append(" AND CREATED_DATE >= ?");
            }

            if (tsf.getCreatedTo() != null) {
                sql.append(" AND CREATED_DATE < ?");
            }

            sql.append(" ORDER BY CREATED_DATE ASC");

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setInt(1, userId);

            int psIdx = 2;

            if (tsf.getCreatedFrom() != null) {
                ps.setDate(psIdx++, new java.sql.Date(tsf.getCreatedFrom().getTime()));
            }

            if (tsf.getCreatedTo() != null) {
                ps.setDate(psIdx++, new java.sql.Date(tsf.getCreatedTo().getTime()));
            }

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                Task task = Task.builder()
                        .id(result.getInt("id"))
                        .title(result.getString("title"))
                        .status(result.getBoolean("status"))
                        .createdDate(result.getTimestamp("created_date"))
                        .changedDate(result.getTimestamp("changed_date"))
                        .build();
                allTask.add(task);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return allTask;
    }

    /**
     * Retrieves the task history for the given user.
     *
     * @param userId the ID of the user whose task history is to be retrieved
     * @return a list of {@link TaskHistory} objects representing the user's task history
     */
    @Override
    public List<TaskHistory> getTaskHistory(int userId) {
        List<TaskHistory> th = new ArrayList<>();
        try {
            Connection conn = dbConnect.getConn();

            String sql = "SELECT * FROM TASK_HISTORY_VIEW WHERE CREATED_BY = ? ORDER BY CREATED_DATE DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                th.add(TaskHistory.builder()
                        .createdDate(result.getDate("created_date"))
                        .completed(result.getInt("completed"))
                        .totalRecords(result.getInt("total_records"))
                        .build());

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return th;
    }
}
