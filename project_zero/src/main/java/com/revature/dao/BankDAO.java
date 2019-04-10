package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.beans.*;

public interface BankDAO {

	public List<Customer> getCustomer();
	//public getCustByLogin(String first, String second);
	public boolean getCustByLogin(String first, String second);
	public void createCust(Customer bear);
	/*public void updateCust(Customer bear);
	public void deleteCust(Customer bear);*/
}
