package com.DB;

import java.sql.*;

public class DBConnect {
	private static Connection conn;
	public static Connection getConn()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebook-app", "root" , "12015448");
//			System.out.println("Connection established successfully");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
