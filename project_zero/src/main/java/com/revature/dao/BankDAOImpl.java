package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
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
	Connection con = null;
	
	public List<Customer> getCustomer(){
		List<Customer> cust = new ArrayList<Customer>();
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
				cust.add(new Customer(id ,username, password, fName, lName));
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
	public boolean getCustByLogin(String first, String second) {
		PreparedStatement myStmt = null;
		boolean x = false;
		ResultSet rs = null;
		try 
		  { 
		  Connection con = ConnectionUtil.getConnectionFromFile("//Users//shaneflynn//GitRepos//Bank//project_zero//src//main//java//resources//Connection.prop");
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
		  } finally {
			    try { rs.close(); } catch (Exception e) { /* ignored */ }
			    try { myStmt.close(); } catch (Exception e) { /* ignored */ }
			    try { con.close(); } catch (Exception e) { /* ignored */ }
			}
		  return x;

	}
	public void createCust(Customer user) {
		//Create PreparedStatement object to execute DML queries.
        PreparedStatement stmt = null;
        //Some exception handling with connecting to a file.
		try ( Connection con = ConnectionUtil.getConnectionFromFile("//Users//shaneflynn//GitRepos//Bank//project_zero//src//main//java//resources//Connection.prop")) {
			
			//Writing DML query, then using the PreparedStatement helper methods to later execute the query.
			stmt = con.prepareStatement("INSERT INTO BANKUSER (USER_ID, USERNAME, PASSWORD, FIRST_NAME ,LAST_NAME) VALUES (?,?,?,?,?)");
			stmt.setInt(1, user.getId());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getFname());
			stmt.setString(5, user.getLname());
			
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
			             } finally {
			 			    //try { rs.close(); } catch (Exception e) { /* ignored */ }
						    try { stmt.close(); } catch (Exception e) { /* ignored */ }
						    try { con.close(); } catch (Exception e) { /* ignored */ }
						}
		      }
		
	}
	
	
	public void createAcc(String Atype, int uid) {
		//int id; 
		PreparedStatement stmt = null;
		//ResultSet rs;
			
		try ( Connection con = ConnectionUtil.getConnectionFromFile("//Users//shaneflynn//GitRepos//Bank//project_zero//src//main//java//resources//Connection.prop")) {
			/*
			stmt3 = con.prepareStatement("SELECT USER_ID FROM BANKUSER WHERE FIRST_NAME = ? AND LAST_NAME = ?");
			stmt3.setString(1, user.getFname());
			stmt3.setString(2, user.getLname());
			
			rs = stmt3.executeQuery();
			if(rs.next()) {
				id = rs.getInt("USER_ID");
			}*/
		//Writing DML query, then using the PreparedStatement helper methods to later execute the query.
		stmt = con.prepareStatement("INSERT INTO ACCOUNTS (ACCOUNT_TYPE, USER_ID) VALUES (?,?)");
		stmt.setString(1, Atype);
		stmt.setInt(2, uid);
		
		
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
		             }finally {
		 			    //try { rs.close(); } catch (Exception e) { /* ignored */ }
					    try { stmt.close(); } catch (Exception e) { /* ignored */ }
					    try { con.close(); } catch (Exception e) { /* ignored */ }
					}
	      }
	
}
	public int getUserId(String first, String second) {
		PreparedStatement myStmt = null;
		int x = 0;
		ResultSet rs = null;
		try 
		  { 
		  Connection con = ConnectionUtil.getConnectionFromFile("//Users//shaneflynn//GitRepos//Bank//project_zero//src//main//java//resources//Connection.prop");
		  myStmt = con.prepareStatement("SELECT USER_ID FROM BANKUSER WHERE USERNAME = ? AND PASSWORD =?");
		  myStmt.setString(1, first);
		  myStmt.setString(2, second);
		  rs = myStmt.executeQuery();
		  while (rs.next()) {
			  int userId = rs.getInt("USER_ID");
			  x = userId;
		  }
		  }
		catch (SQLException e) 
		  { 
			  e.printStackTrace(); 
		  }
		  catch (IOException e) 
		  {
			  e.printStackTrace(); 
		  } finally {
			    try { rs.close(); } catch (Exception e) { /* ignored */ }
			    try { myStmt.close(); } catch (Exception e) { /* ignored */ }
			    try { con.close(); } catch (Exception e) { /* ignored */ }
			}
		  return x;
		
	}
	
	@Override
	public double getBalanceById(int id) {
		PreparedStatement myStmt = null;
		double x = 0;
		ResultSet rs = null;
		try 
		  { 
		  Connection con = ConnectionUtil.getConnectionFromFile("//Users//shaneflynn//GitRepos//Bank//project_zero//src//main//java//resources//Connection.prop");
		  myStmt = con.prepareStatement("SELECT BALANCE FROM ACCOUNTS WHERE USER_ID = ?");
		  myStmt.setInt(1, id);
		  rs = myStmt.executeQuery();
		  while (rs.next()) {
			  double balance = rs.getInt("BALANCE");
			  x = balance;
		  }
		  }
		catch (SQLException e) 
		  { 
			  e.printStackTrace(); 
		  }
		  catch (IOException e) 
		  {
			  e.printStackTrace(); 
		  } finally {
			    try { rs.close(); } catch (Exception e) { /* ignored */ }
			    try { myStmt.close(); } catch (Exception e) { /* ignored */ }
			    try { con.close(); } catch (Exception e) { /* ignored */ }
			}
		  return x;
	}
	@Override
	public void deleteCust(Customer bear) {
		
		
	}
	@Override
	public List<Superuser> getSuperuser() {
		List<Superuser> superU = new ArrayList<Superuser>();
		try 
		  { 
		  Connection con = ConnectionUtil.getConnectionFromFile("//Users//shaneflynn//GitRepos//Bank//project_zero//src//main//java//resources//Connection.prop");
		  String sql = "SELECT * FROM BANKUSER FULL JOIN ACCOUNTS ON BANKUSER.USER_ID = ACCOUNTS.USER_ID";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("USER_ID");
				String username = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				String fName = rs.getString("FIRST_NAME");
				String lName = rs.getString("LAST_NAME");
				int accId = rs.getInt("ACCOUNT_ID");
				double balance = rs.getDouble("BALANCE");
				String type = rs.getString("ACCOUNT_TYPE");
				superU.add(new Superuser(id ,username, password, fName, lName, accId, balance, type));
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
		return superU;
	}
	@Override
	public void updateSuper(int id, String fname, String lname, String username, String password) {
		PreparedStatement stmt = null;
        //Some exception handling with connecting to a file.
		try ( Connection con = ConnectionUtil.getConnectionFromFile("//Users//shaneflynn//GitRepos//Bank//project_zero//src//main//java//resources//Connection.prop")) {
			
			//Writing DML query, then using the PreparedStatement helper methods to later execute the query.
			stmt = con.prepareStatement("UPDATE BANKUSER\n" + 
										"SET USERNAME = ?, PASSWORD = ?, FIRST_NAME = ?, LAST_NAME =?\n" + 
										"WHERE USER_ID = ?");
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, fname);
			stmt.setString(4, lname);
			stmt.setInt(5, id);
			
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
			             } finally {
			 			    //try { rs.close(); } catch (Exception e) { /* ignored */ }
						    try { stmt.close(); } catch (Exception e) { /* ignored */ }
						    try { con.close(); } catch (Exception e) { /* ignored */ }
						}
		      }
	}
	public void deleteSuper(int id) {
		double x = getBalanceById(id);
		if (x == 0) {
			CallableStatement stmt = null;
	        //Some exception handling with connecting to a file.
			try ( Connection con = ConnectionUtil.getConnectionFromFile("//Users//shaneflynn//GitRepos//Bank//project_zero//src//main//java//resources//Connection.prop")) {
				stmt = con.prepareCall("{call SP_DELETE_USER_AND_ACCOUNT (?)}");
				stmt.setInt(1, id);
				
				
			    stmt.execute();
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
			             } finally {
			 			    //try { rs.close(); } catch (Exception e) { /* ignored */ }
						    try { stmt.close(); } catch (Exception e) { /* ignored */ }
						    try { con.close(); } catch (Exception e) { /* ignored */ }
						}
		      }
			
		} else if (x > 0){
			System.out.println("Cannot delete an account that has money in balance.");
		
		} else {
			CallableStatement stmt = null;
	        //Some exception handling with connecting to a file.
			try ( Connection con = ConnectionUtil.getConnectionFromFile("//Users//shaneflynn//GitRepos//Bank//project_zero//src//main//java//resources//Connection.prop")) {
				stmt = con.prepareCall("{call SP_DELETE_USER (?)}");
				stmt.setInt(1, id);
				
				
			    stmt.execute();
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
			             } finally {
			 			    //try { rs.close(); } catch (Exception e) { /* ignored */ }
						    try { stmt.close(); } catch (Exception e) { /* ignored */ }
						    try { con.close(); } catch (Exception e) { /* ignored */ }
						}
		      }
		}
			
	}
	

	
	

}
