package com.kce.main;


import com.kce.bean.TransferBean;
import com.kce.service.BankService;

public class Main {
	public static void main(String[] args) {
		// View Balance
		BankService bankService=new BankService();
		System.out.println(bankService.checkBalance("1234567890"));		 
		// TransferMoney
		TransferBean transferBean = new TransferBean();		 
		transferBean.setFromAccountNumber("1234567890");
		transferBean.setAmount(500);
		transferBean.setToAccountNumber("1234567891");
		transferBean.setDateOfTransaction(new java.util.Date());		 
		System.out.println(bankService.transfer(transferBean));
		
		System.out.println(bankService.checkBalance("1234567890"));	
		}

}