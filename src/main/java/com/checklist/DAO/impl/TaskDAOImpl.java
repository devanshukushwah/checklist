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
			
			String sql = "SELECT * FROM TASK";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				allTask.add(new Task(result.getInt("id"), result.getString("title"), result.getBoolean("status")));
			}
			
		} catch (Exception ex) {
			
			System.out.println(ex.getMessage());
		}
		// TODO Auto-generated method stub
		return allTask;
	}
	
}
