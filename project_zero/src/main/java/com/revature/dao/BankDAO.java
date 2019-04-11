package com.revature.dao;

import java.util.List;

import com.revature.beans.*;

public interface BankDAO {

	public List<Customer> getCustomer();
	public void createCust(Customer bear);
	public Customer getCustomer(String user, String pass);
	public double getMoney(Customer d, String type);
	public boolean getCustByLogin(String first, String second);
	public double Withdraw(Customer c, double amount);
	public double Deposit(Customer c, double amount);
	public void createAcc(String type, int id);
	public boolean getAccountType(Customer c, String type);
	
	public void updateCust(Customer bear);
	public void deleteCust(Customer bear);
	
	
	public List<Account> getAccount();
	public Customer getAcctById(int id);
	
	public void updateAcc(Account up);
	public void deleteAcc(Account del);
	
	
	
}
