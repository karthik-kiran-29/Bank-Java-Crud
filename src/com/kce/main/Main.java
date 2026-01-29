package com.kce.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import com.kce.bank.*;
import com.kce.bean.TransferBean;
import com.kce.dao.BankDAO;
import com.kce.service.BankService;

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
		
		BankService service = new BankService();
		
		System.out.println(service.checkBalance("1234567892"));
		
		TransferBean transferbean = new TransferBean(1,"1234567892","1234567893",new Date(),1000f);
		
		System.out.println(service.transfer(transferbean));
			
			
			
			
			
			
	}
}
