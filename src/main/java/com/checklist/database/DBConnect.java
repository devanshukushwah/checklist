package com.checklist.database;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DBConnect {
	private static Connection conn = null;
	
    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    @Value("${spring.datasource.password}")
    private String datasourcePassword;
	
	public Connection getConn() {
		try {
			if(conn == null) {
				
		        // Load the PostgreSQL JDBC driver
		        Class.forName("org.postgresql.Driver");

		        // Establish connection
		        conn = DriverManager.getConnection(datasourceUrl, datasourceUsername, datasourcePassword);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return conn;
	}
}
