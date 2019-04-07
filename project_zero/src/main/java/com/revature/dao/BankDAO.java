package com.revature.dao;

import java.util.List;

import com.revature.beans.*;

public interface BankDAO {

	public List<Customer> getCustomer();
	public Customer getCustById(int id);
	public void createCust(Customer bear);
	public void updateCust(Customer bear);
	public void deleteCust(Customer bear);
}
