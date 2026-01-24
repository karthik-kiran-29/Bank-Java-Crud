package com.kce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kce.bank.DBUtil;


public class BankDAO {
	
	public boolean validateAccount(String accountNumber) throws SQLException {
		Connection con =  DBUtil.getConnection();
		
		String query  = "select Account_Number from account_tbl where Account_Number = ? ";
		
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setString(1,accountNumber);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			return true;
		}
		
		return false;
		
	}
}
