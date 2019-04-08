package com.revature.dao;

import java.io.IOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.util.*;
import com.revature.beans.*;
public class BankDAOImpl implements BankDAO {
	
	public List<Customer> getCustomer(){
		List<Customer> cust = new ArrayList<Customer>();
		// try-with-resources... resources will be closed at the end of the block
		// works for all AutoCloseable resources
		try 
		  { 
		  Connection con = ConnectionUtil.getConnectionFromFile("//Users//shaneflynn//GitRepos//Bank//project_zero//src//main//java//resources//Connection.prop");
		  String sql = "SELECT USER_ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME FROM BANKUSER";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("USER_ID");
				String username = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				String fName = rs.getString("FIRST_NAME");
				String lName = rs.getString("LAST_NAME");
				cust.add(new Customer(id, username, password, fName, lName));
		  }
		  }
		  catch (SQLException e) 
		  { 
			  e.printStackTrace(); 
		  }
		  catch (IOException e) 
		  {
			  e.printStackTrace(); 
		  }
		return cust;
		
		
		/*String sql = "SELECT * "
		+ "FROM BANKUSER";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(sql);
while (rs.next()) {
	int id = rs.getInt("USER_ID");
	String username = rs.getString("USERNAME");
	String password = rs.getString("PASSWORD");
	String fName = rs.getString("FIST_NAME");
	String lName = rs.getString("LAST_NAME");
	us.add(new Customer(id, username, password, fName, lName);*/
		//return cust;
		
	}
	/*public User getUserById(int id) {
		
		return null;
	}
	public void createCust(Customer bear) {
		
	}
	public void updateCust(Customer bear) {
		
	}
	public void deleteCust(Customer bear) {
		
	}*/

}
