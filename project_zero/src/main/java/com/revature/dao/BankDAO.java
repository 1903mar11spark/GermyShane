package com.revature.dao;

import java.util.List;

import com.revature.beans.*;

public interface BankDAO {

	public List<Customer> getCustomer();
	public Customer getCustById(int id);
	public void createCust(Customer bear);
	public void updateCust(Customer bear);
	public void deleteCust(Customer bear);
	public boolean getCustByLogin(String first, String second);
	
	public List<Account> getAccount();
	public Customer getAcctById(int id);
	public void createAcc(Account acc);
	public void updateAcc(Account up);
	public void deleteAcc(Account del);
}
