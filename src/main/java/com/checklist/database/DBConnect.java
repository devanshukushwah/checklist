package com.checklist.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	private static Connection conn;
	
	public static Connection getConn() {
		
		try {
			if(conn == null) {
				
				String jdbcURL = "jdbc:postgresql://localhost:5432/checklistDB";
				String jdbcUsername = "root";
				String jdbcPassword = "rootpassword";
				
		        // Load the PostgreSQL JDBC driver
		        Class.forName("org.postgresql.Driver");

		        // Establish connection
		        conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return conn;
	}
}
