package com.zumba.resource;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseResource {
	private static Connection con;
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/my_zumba_db", "root", "Simplilearn@123");
		}catch(Exception e) {
			System.err.println(e);
		}
	}
	public static Connection getDbConnection() {
		try {
			return con;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
}
