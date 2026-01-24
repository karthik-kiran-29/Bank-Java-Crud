package com.kce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kce.bank.DBUtil;
import com.kce.bean.TransferBean;


public class BankDAO {
	
	private Connection con =  DBUtil.getConnection();
	
	public boolean validateAccount(String accountNumber) throws SQLException {
		String query  = "select Account_Number from account_tbl where Account_Number = ? ";
		
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setString(1,accountNumber);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			return true;
		}
		
		return false;
		
	}
	
	public float findBalance(String accountNumber) throws Exception {
		if(validateAccount(accountNumber)) {
			
			String query  = "select Balance from account_tbl where Account_Number = ? ";
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1,accountNumber);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			return rs.getFloat("balance");
		}else {
			return -1;
		}
	}
	
	public boolean transferMoney(TransferBean transferbean) {
		if(transferbean!=null) {
			try {
				// we are using autocommit off so SQL doesn't get pre-compiled
				con.setAutoCommit(false); // used for transaction
				
				Statement smt = con.createStatement();
				
				int newBalance = (int)( findBalance(transferbean.getFromAccountNumber()) - transferbean.getAmount());
				
				smt.executeUpdate("UPDATE account_tbl SET balance" + newBalance + "where Account_Number= "  + transferbean.getFromAccountNumber() )
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}else return false;
	}
	
	public boolean updateBalance(String accountNumber,float newBalance) {
		try {
			if(validateAccount(accountNumber)) {
				PreparedStatement ps = con.prepareStatement("UPDATE account_tbl SET balance = ? where Account_Number = ?");
				
				ps.setString(2, accountNumber);
				ps.setFloat(1, newBalance);
				
				
				System.out.println(ps.executeUpdate());
				
				return true;
				
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
