package com.kce.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static Connection con;
	
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			if(con==null) {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","scott");
			}
			System.out.println("DB Connection Successful");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
}
