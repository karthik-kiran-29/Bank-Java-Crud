package com.kce.service;

import com.kce.bank.InsufficientFundsException;
import com.kce.bean.TransferBean;
import com.kce.dao.BankDAO;

public class BankService {
	
	private BankDAO bankdao = new BankDAO();
	
	public String checkBalance(String accountNumber) {
		try {
		if(bankdao.validateAccount(accountNumber)) {
				String balance = "BALANCE: " + bankdao.findBalance(accountNumber) + "";
				
				return balance;
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Invalid Account";
	}
	
	public String transfer(TransferBean transferbean) {
		if(transferbean!=null) {
			try {
				if(bankdao.validateAccount(transferbean.getFromAccountNumber()) && bankdao.validateAccount(transferbean.getToAccountNumber())) {
					if(bankdao.findBalance(transferbean.getFromAccountNumber())>=transferbean.getAmount()){
						//update from account balance
						bankdao.updateBalance(transferbean.getFromAccountNumber(), bankdao.findBalance(transferbean.getFromAccountNumber()) - transferbean.getAmount());
						
						//update to account balance
						bankdao.updateBalance(transferbean.getToAccountNumber(), bankdao.findBalance(transferbean.getToAccountNumber()) + transferbean.getAmount());
						
						bankdao.transferMoney(transferbean);
						
						return "SUCCESS";
					}
					
					throw new InsufficientFundsException(); 
				}
				
				return "INVALID ACCOUNT NUMBER";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "INVALID TRANSFER BEAN";
	}
}
