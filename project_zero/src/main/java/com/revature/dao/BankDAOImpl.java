package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
		
		try 
		  { 
		  Connection con = ConnectionUtil.getConnectionFromFile("C:/gitrepos/Bank/project_zero/src/main/java/resources/Connection.prop");
		  String sql = "SELECT USER_ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME FROM BANKUSER";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				//int id = rs.getInt("USER_ID");
				String username = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				String fName = rs.getString("FIRST_NAME");
				String lName = rs.getString("LAST_NAME");
				cust.add(new Customer(username, password, fName, lName));
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
	}
	
	public Customer getCustById(int id) {
		
		return null;
	}
	
	public void createCust(Customer user) {
		//Create PreparedStatement object to execute DML queries.
        PreparedStatement stmt = null;
       
        
        //Some exception handling with connecting to a file.
		try ( Connection con = ConnectionUtil.getConnectionFromFile("C:/gitrepos/Bank/project_zero/src/main/java/resources/Connection.prop")) {
			
			//Writing DML query, then using the PreparedStatement helper methods to later execute the query.
			stmt = con.prepareStatement("INSERT INTO BANKUSER (USERNAME, PASSWORD, FIRST_NAME ,LAST_NAME) VALUES (?,?,?,?)");
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getFname());
			stmt.setString(4, user.getLname());
			
		    stmt.execute();
		    
		    
           //More exception handling.
	      } catch (SQLException sqlEx) {
	             sqlEx.printStackTrace();
	             System.exit(1);  
	      } catch (IOException e1) {
			e1.printStackTrace();
			} finally {
		             try { 	//Ideally would close connection here.
		                    stmt.close();  
			             } catch (Exception e) {
			                    System.exit(1); 
			             }
		      }
		
	}
	
	public boolean getCustByLogin(String first, String second) {
		PreparedStatement myStmt = null;
		boolean x = false;
		ResultSet rs = null;
		try 
		  { 
		  Connection con = ConnectionUtil.getConnectionFromFile("C:/gitrepos/Bank/project_zero/src/main/java/resources/Connection.prop");
		  myStmt = con.prepareStatement("SELECT USERNAME, PASSWORD FROM BANKUSER WHERE USERNAME = ? AND PASSWORD =?");
		  myStmt.setString(1, first);
		  myStmt.setString(2, second);
		  rs = myStmt.executeQuery();
		  if (!rs.next()) {
			  System.out.println("Wrong Username and Password.");
		  } else {
			  x = true;
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
		  return x;

}
	public void updateCust(Customer bear) {
		
	}
	public void deleteCust(Customer bear) {
		
	}
	
	public List<Account> getAccount(){
		
		return null;
	}
	public Customer getAcctById(int id) {
		
		return null;
	}
	
	public void createAcc(Account acc) {
		
			PreparedStatement stmt = null;
			Customer user =  acc.getUser();
			
			
			
			
			try ( Connection con = ConnectionUtil.getConnectionFromFile("C:/gitrepos/Bank/project_zero/src/main/java/resources/Connection.prop")) {
			
			stmt = con.prepareStatement("INSERT INTO ACCOUNTS ( BALANCE, ACCOUNT_TYPE ,USER_ID) VALUES (?,?,?)");
			stmt.setDouble(1, acc.getBalance());
			stmt.setString(2, acc.getType());
			stmt.setInt(3, user.getId());
			
			
		    stmt.execute();
           //More exception handling.
	      } catch (SQLException sqlEx) {
	             sqlEx.printStackTrace();
	             System.exit(1);  
	      } catch (IOException e1) {
			e1.printStackTrace();
			} finally {
		             try { 	//Ideally would close connection here.
		                    stmt.close();  
			             } catch (Exception e) {
			                    System.exit(1); 
			             }
		      }
		
	}
	public void updateAcc(Account up) {
		
		
	}
	public void deleteAcc(Account del) {
		
		
	}
}
