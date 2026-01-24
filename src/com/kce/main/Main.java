package com.kce.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kce.bank.*;
import com.kce.dao.BankDAO;

public class Main {
	public static void main(String[] args) throws SQLException, InsufficientFundsException {
		Connection con = DBUtil.getConnection();
		
		Statement smt = con.createStatement();
		
		String query = "select * from transfer_tbl;";
		
		ResultSet res = smt.executeQuery(query);
		
		while(res.next()) {
			System.out.println(""+ res.getString(2) + "");
		}
		
		BankDAO bankdao = new BankDAO();
		
		if(bankdao.validateAccount("1234567892")) {
			System.out.print("true vanthurchu");
		}
	}
}
