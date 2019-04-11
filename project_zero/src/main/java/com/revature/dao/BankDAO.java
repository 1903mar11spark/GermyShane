package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.beans.*;

public interface BankDAO {

	public List<Superuser> getSuperuser();
	public List<Customer> getCustomer();
	//public getCustByLogin(String first, String second);
	public boolean getCustByLogin(String first, String second);
	public void createCust(Customer bear);
	//public void updateCust(Customer bear);
	public void deleteCust(Customer bear);
	
	//public List<Account> getAccount();
	//public Customer getAcctById(int id);
	public void createAcc(String type, int uid);
	//public void updateAcc(Account up);
	//public void deleteAcc(Account del);
	public int getUserId(String user, String pass);
	public double getMoney(int id);
	public void updateSuper(int id, String fname, String lname, String username, String password); 
}
