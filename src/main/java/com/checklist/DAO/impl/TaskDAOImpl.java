package com.checklist.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.checklist.DAO.TaskDAO;
import com.checklist.database.DBConnect;
import com.checklist.model.PageRequest;
import com.checklist.model.PageResponse;
import com.checklist.model.Task;
import com.checklist.model.TaskHistory;
import com.checklist.model.TaskSearchFilter;

import lombok.AllArgsConstructor;

/**
 * Implementation of the {@link TaskDAO} interface for interacting with the task-related
 * data in the database. This class handles CRUD operations on tasks, task history, 
 * and task status updates.
 */
@Component
public class TaskDAOImpl implements TaskDAO {
	
	@Value("${defaultPageSize:10}")
	private Integer defaultPageSize;

	@Autowired
    private DBConnect dbConnect;

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
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
     * {@inheritDoc}
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
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public PageResponse<List<TaskHistory>>getTaskHistory(int userId, PageRequest pr) {
        PageResponse<List<TaskHistory>> prth = new PageResponse<>();
        try {
            Connection conn = dbConnect.getConn();
            
            StringBuilder sb = new StringBuilder("SELECT thv.*, COUNT(1) OVER() TOTAL_COUNT FROM TASK_HISTORY_VIEW thv WHERE CREATED_BY = ? ORDER BY CREATED_DATE DESC LIMIT ? OFFSET ?");

			// page 1 limit 10, offset 0
			// page 2 limit 10, offset 10
			// page 3 limit 10, offset 20
			// page 4 limit 10, offset 30

			int limit = pr.getPageSize() != null && pr.getPageSize() > 0 ? pr.getPageSize() : this.defaultPageSize;
			int offset = pr.getPageNo() != null && pr.getPageNo() > 1 ? (pr.getPageNo() - 1) * limit : 0;

			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.setInt(1, userId);
			ps.setInt(2, limit);
			ps.setInt(3, offset);

			ResultSet result = ps.executeQuery();
			int totalCount = 0;
			List<TaskHistory> th = new ArrayList<>();
			while (result.next()) {
				th.add(TaskHistory.builder().createdDate(result.getDate("created_date"))
						.completed(result.getInt("completed")).totalTasks(result.getInt("total_tasks")).build());
				totalCount = result.getInt("TOTAL_COUNT");
			}
			prth.setData(th);
			prth.setTotalCount(totalCount);					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return prth;
    }
}
