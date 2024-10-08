package com.checklist.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Component;

import com.checklist.DAO.UserDAO;
import com.checklist.database.DBConnect;
import com.checklist.exception.DatabaseError;
import com.checklist.exception.UserNotFound;
import com.checklist.model.Task;
import com.checklist.model.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class UserDAOImpl implements UserDAO {

	private DBConnect dbConnect;
	
	@Override
	public int create(User user) throws DatabaseError {
		
		try {
			Connection conn = dbConnect.getConn();
			
			String sql = "INSERT INTO \"USER\"(FULL_NAME, EMAIL, PASSWORD) VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getFullName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			int rows = ps.executeUpdate();
			return rows;
		} catch (Exception ex) {	
			throw new DatabaseError();
		}
	}

	@Override
	public User getUser(String email, String password) throws DatabaseError {
		try {
			Connection conn = dbConnect.getConn();
			
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
			throw new DatabaseError();
		}
		
		return null;
	}

	@Override
	public boolean userExists(String email) throws DatabaseError {
		try {
			Connection conn = dbConnect.getConn();
			
			String sql = "SELECT EXISTS (SELECT 1 FROM \"USER\" WHERE EMAIL = ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getBoolean(1);
			}
		} catch (Exception ex) {	
			throw new DatabaseError();
		}
		
		return false;
	}

}
