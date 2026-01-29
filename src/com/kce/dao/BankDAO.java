package com.kce.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.kce.bank.DBUtil;
import com.kce.bean.TransferBean;


public class BankDAO {
	
	private int  seqNumber = 0;
	
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
		try {
	        String query = "INSERT INTO transfer_tbl (Transaction_id, Account_Number , Beneficiary_acc_number , Transaction_Date , Transaction_Amount)  VALUES(?,?,?,?,?)";
	        
			PreparedStatement ps  = con.prepareStatement(query);
			
			ps.setInt(1, generateSequenceNumber());
			ps.setString(2,transferbean.getFromAccountNumber());
			ps.setString(3,transferbean.getToAccountNumber());
			
			Date sqlDate = new Date(transferbean.getDateOfTransaction().getTime());
			
			ps.setDate(4,sqlDate);
			ps.setInt(5,(int) transferbean.getAmount());
			
			int rows  = ps.executeUpdate();
			
			if(rows>0) {
				return true;
			}
			
			return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
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
	
	public int generateSequenceNumber() {
		try {
			Statement smt = con.createStatement();
			
			ResultSet rs = smt.executeQuery("SELECT  MAX(Transaction_id) from transfer_tbl");
			
			rs.next();
			
			return rs.getInt(1)+1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	
}
