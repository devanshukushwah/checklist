package com.checklist.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Component;

import com.checklist.DAO.UserDAO;
import com.checklist.database.DBConnect;
import com.checklist.model.Task;
import com.checklist.model.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class UserDAOImpl implements UserDAO {

	@Override
	public int create(User user) {
		
		try {
			Connection conn = DBConnect.getConn();
			
			String sql = "INSERT INTO \"USER\"(FULL_NAME, EMAIL, PASSWORD) VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getFullName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			int rows = ps.executeUpdate();
			return rows;
		} catch (Exception ex) {	
			ex.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public User getUser(String email, String password) {
		try {
			Connection conn = DBConnect.getConn();
			
			String sql = "SELECT * FROM \"USER\" WHERE EMAIL = ? AND PASSWORD = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(email);
				user.setFullName(rs.getString("full_name"));
				return user;
			}
		} catch (Exception ex) {	
			ex.printStackTrace();
		}
		
		return null;
	}

}
