package com.checklist.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.checklist.DAO.TaskDAO;
import com.checklist.model.Task;
import com.checklist.database.DBConnect;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class TaskDAOImpl implements TaskDAO {

	@Override
	public List<Task> getHomeTask() {
		List<Task> allTask = new ArrayList<>();
		try {
			Connection conn = DBConnect.getConn();
			
			String sql = "SELECT * FROM TASK ORDER BY CREATED_DATE ASC";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				allTask.add(new Task(result.getInt("id"), result.getString("title"), result.getBoolean("status")));
			}
			
		} catch (Exception ex) {	
			System.out.println(ex.getMessage());
		}
		return allTask;
	}

	@Override
	public boolean updateStatus(int id, boolean status) {
		try {
			Connection conn = DBConnect.getConn();
			
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

	@Override
	public boolean addTask(Task task) {
		try {
			Connection conn = DBConnect.getConn();
			
			String sql = "INSERT INTO TASK(TITLE, STATUS) VALUES (?, FALSE)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, task.getTitle());
			int rows = ps.executeUpdate();
			
			return rows > 0;
			
		} catch (Exception ex) {	
			System.out.println(ex.getMessage());
		}
		return false;
	}
	
}
