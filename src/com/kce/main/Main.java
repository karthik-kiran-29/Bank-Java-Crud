package com.kce.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.kce.bank.*;
import com.kce.dao.BankDAO;

public class Main {
	public static void main(String[] args) throws Exception {
		Connection con = DBUtil.getConnection();
		
		Statement smt = con.createStatement();
		
		String query = "select * from transfer_tbl;";
		
		ResultSet res = smt.executeQuery(query);
		
		while(res.next()) {
			System.out.println(""+ res.getString(2) + "");
		}
		
		BankDAO bankdao = new BankDAO();
		
		if(bankdao.validateAccount("1234567892")) {
			System.out.println("true vanthurchu");
		}
		
		System.out.println(bankdao.findBalance("1234567892"));
		
		if(bankdao.updateBalance("1234567892", 1000.00f)) {
			System.out.println("Updated Successfully");
		}
		
		if(bankdao.transferMoney(null))
		
	}
}
