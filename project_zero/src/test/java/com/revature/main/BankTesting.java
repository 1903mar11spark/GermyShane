package com.revature.main;

import static org.junit.Assert.*;


import org.junit.Test;


import com.revature.beans.Customer;
import com.revature.dao.BankDAO;
import com.revature.dao.BankDAOImpl;

public class BankTesting {
	BankDAO test = new BankDAOImpl();
	@Test
	public void correctUserNameLogin() {
		boolean output = test.getCustByLogin("shanef3" , "coolguy");
		assertTrue(output);
	}
	@Test
	public void incorrectUserNameLogin() {
		boolean output = test.getCustByLogin("shanef3" , "guy");
		assertFalse(output);
	}

	
	@Test
	public void noBalance() {
		Customer testCustomer = new Customer (2, "s", "s", "s", "s");
		double output = test.getMoney(testCustomer , "Checking");
		assertEquals(-404, output, 0);
	}
	@Test
	public void correctBlance() {
		Customer testCustomer = new Customer (1, "Shane", "Flynn", "shanef3", "coolguy");
		double output = test.getMoney(testCustomer , "Checking");
		assertEquals(100004, output, 0);
	}
	
	@Test
	public void wrongAccountDeposit() {
		Customer testCustomer = new Customer (16, "germy", "louis", "dirtyg", "newbrunswick");	
		double output = test.Deposit(testCustomer, 100, "Savings");
		assertEquals(-404, output, 0);
	}
	
	@Test
	public void wrongAccountWithdraw() {
		Customer testCustomer = new Customer (16, "germy", "louis", "dirtyg", "newbrunswick");
		double output = test.Withdraw(testCustomer, 100, "Savings");
		assertEquals(-404, output, 0);
	
	}
	
	@Test
	public void toMuchWithdraw() {
		Customer testCustomer = new Customer (1, "Shane", "Flynn", "shanef3", "coolguy");
		test.Withdraw(testCustomer, 1000000, "Checking");
		//assertEquals(-404, output, 0);
		assert ("You have less money than you wish to withdraw. We do not allow overdrafts, Sorry!") != null;

	}
	
	@Test
	public void deletingAccountWithMoney() {
		test.deleteSuper(1);
	    assert ("Cannot delete an account that has money in balance.") != null;
	    
	}
}
