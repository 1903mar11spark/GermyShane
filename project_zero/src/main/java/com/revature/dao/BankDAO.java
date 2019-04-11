package com.revature.dao;

import java.util.List;

import com.revature.beans.*;

public interface BankDAO {

	public List<Customer> getCustomer();
	public List<Superuser> getSuperuser();
	
	public void createCust(Customer bear);
	public void createAcc(String type, int id);
	
	public Customer getCustomer(String user, String pass);
	public boolean getCustByLogin(String first, String second);
	
	public double getMoney(Customer d, String type);
	public double Withdraw(Customer c, double amount, String type);
	public double Deposit(Customer c, double amount,String type);
	
	public void updateSuper(int id, String fname, String lname, String username, String password);
	
	public void updateCust(Customer bear);
	public void deleteCust(Customer bear);
	
	
}
