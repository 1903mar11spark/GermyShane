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
		Connection con = null;
		ResultSet rs = null;
		
		try 
		  { 
		   con = ConnectionUtil.getConnectionFromFile("C:/gitrepos/Bank/project_zero/src/main/java/resources/Connection.prop");
		   String sql = "SELECT USER_ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME FROM BANKUSER";
		   Statement stmt = con.createStatement();
		   rs = stmt.executeQuery(sql);
		   
				while (rs.next()) {
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
		  }finally {
			    try { rs.close(); } catch (Exception e) { /* ignored */ }
			    try { con.close(); } catch (Exception e) { /* ignored */ }
			}
		
			return cust;
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
	      } catch (IOException e1) {e1.printStackTrace();} 
					finally {
				             try {stmt.close();}
				             	catch (Exception e) {System.exit(1);}
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
			 } 
			  else {
				  x = true;
				   }
				
		  }
			  catch (SQLException e) 
			  {e.printStackTrace();}
			  catch (IOException e) 
			  { e.printStackTrace();}
		  return x;

	}
	
	public void createAcc(String type, int id) {
		
				PreparedStatement stmt = null;
					
				try ( Connection con = ConnectionUtil.getConnectionFromFile("C:/gitrepos/Bank/project_zero/src/main/java/resources/Connection.prop")) {
			
				//Writing DML query, then using the PreparedStatement helper methods to later execute the query.
				stmt = con.prepareStatement("INSERT INTO ACCOUNTS (ACCOUNT_TYPE, USER_ID) VALUES (?,?)");
				stmt.setString(1, type);
				stmt.setInt(2, id);
				
				
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
				             }finally {
							    try { stmt.close(); } catch (Exception e) { /* ignored */ }
							    
							}
		}
	}
	
	public double getMoney(Customer c, String type) {
		double money = -404 ;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try ( Connection con = ConnectionUtil.getConnectionFromFile("C:/gitrepos/Bank/project_zero/src/main/java/resources/Connection.prop")) {
			
			stmt = con.prepareStatement("SELECT BALANCE FROM ACCOUNTS WHERE USER_ID = ? AND ACCOUNT_TYPE = ?");
			stmt.setInt(1, c.getId());
			stmt.setString(2, type);
		    rs = stmt.executeQuery();
		    

		    while(rs.next()) {
		    	money = rs.getDouble("BALANCE");

		    }
	    	if(money == -404) {
			    System.out.println("Account not found.\n");
			    	return -404;
				}
			    
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
		             finally {
		     		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		     		    //try { con.close(); } catch (Exception e) { /* ignored */ }
		      }
		
		
			}
		return money;
	}
	
	public double Deposit(Customer c, double amount, String type) {

		double money = 0, newBal = 0, error = -404;
		PreparedStatement stmt = null, stmt2;
		
		ResultSet rs = null, rs2 = null;
		try ( Connection con = ConnectionUtil.getConnectionFromFile("C:/gitrepos/Bank/project_zero/src/main/java/resources/Connection.prop")) {
			
			stmt = con.prepareStatement("SELECT BALANCE FROM ACCOUNTS WHERE USER_ID = ? AND ACCOUNT_TYPE = ?");
			stmt.setInt(1, c.getId());
			stmt.setString(2, type);
		    rs = stmt.executeQuery();
		    while(rs.next()) {
		    	money = rs.getDouble("BALANCE");
		    	error = money;
		    }
		    
		    if(error == -404) {
			    System.out.println("You dont have a "+type+" account to deposit into.\n");
			    	return error;
				}
			    money = money + amount;
			    
			    stmt2 = con.prepareStatement("UPDATE ACCOUNTS SET BALANCE = ? WHERE USER_ID = ? AND ACCOUNT_TYPE = ?");
				stmt2.setDouble(1, money);
				stmt2.setInt(2, c.getId());
				stmt2.setString(3, type);
				rs2 = stmt2.executeQuery();
				
				while(rs.next()) {
			    	money = rs.getDouble("BALANCE");
			    }
			    
				newBal = money;
		   
           //More exception handling.
	      } catch (SQLException sqlEx) {
	             sqlEx.printStackTrace();
	             System.exit(1);  
	      } catch (IOException e1) {e1.printStackTrace();} 
				finally {
		            	try {stmt.close();}
			            	catch (Exception e) {}
			     		    		try { rs.close(); } 
			     		    			catch (Exception e) { /* ignored */ }
	     		    
	      
						}
			
		return newBal;

	}	
	
	public double Withdraw(Customer c, double amount, String type) {

		double money = 0, newBal = 0, error = -404;
		PreparedStatement stmt = null, stmt2;
		
		ResultSet rs = null, rs2 = null;
		try ( Connection con = ConnectionUtil.getConnectionFromFile("C:/gitrepos/Bank/project_zero/src/main/java/resources/Connection.prop")) {
			
			stmt = con.prepareStatement("SELECT BALANCE FROM ACCOUNTS WHERE USER_ID = ? AND ACCOUNT_TYPE = ?");
			stmt.setInt(1, c.getId());
			stmt.setString(2, type);
		    
		    rs = stmt.executeQuery();
		    while(rs.next()) {
		    	money = rs.getDouble("BALANCE");
		    	error = money;
		    }
		    
		    if(error == -404) {
			    System.out.println("You dont have a "+type+" account to deposit into.\n");
			    	return error;
				}
		    
			    if(money > amount) {
			    money = money -amount;
			    stmt2 = con.prepareStatement("UPDATE ACCOUNTS SET BALANCE = ? WHERE USER_ID = ? AND ACCOUNT_TYPE = ?");
				stmt2.setDouble(1, money);
				stmt2.setInt(2, c.getId());
				stmt2.setString(3, type);
				
				rs2 = stmt2.executeQuery();
				while(rs.next()) {
			    	money = rs.getDouble("BALANCE");
			    }
				newBal = money;
		    }else {
		    	System.out.println("You have less money than you wish to withdraw. Sorry!");
		    	return money;
		    }
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
		             finally {
		     		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		     		    
		      }
		
			}
			
		
		return newBal;
	


}

	public Customer getCustomer(String user, String pass) {
		PreparedStatement stmt = null ;
		Connection con = null;
		ResultSet rs = null;
		int id = 1;
		String username ="" ,password = "" , fName = "", lName = "" ;
		try 
		  { 
		   con = ConnectionUtil.getConnectionFromFile("C:/gitrepos/Bank/project_zero/src/main/java/resources/Connection.prop");
		  String sql = "SELECT USER_ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME FROM BANKUSER WHERE USERNAME = ? AND PASSWORD = ?";
		  stmt = con.prepareStatement(sql);
		  stmt.setString(1, user);
		  stmt.setString(2, pass);
		  
		  
			 rs = stmt.executeQuery();
			while (rs.next()) {
				 id = rs.getInt("USER_ID");
				 username = rs.getString("USERNAME");
				 password = rs.getString("PASSWORD");
				 fName = rs.getString("FIRST_NAME");
				 lName = rs.getString("LAST_NAME");
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
		catch (NullPointerException e) 
		  { 
			  e.printStackTrace(); 
		  }
		finally {
		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { con.close(); } catch (Exception e) { /* ignored */ }
		}
			Customer thisCust = new Customer(id,username,password,fName,lName);
			return thisCust;
	}


	public void updateCust(Customer cust) {
		
	}
	
	public void deleteCust(Customer cust) {
		
	}
	


	@Override
	public List<Superuser> getSuperuser() {
		List<Superuser> superU = new ArrayList<Superuser>();
		try 
		  { 
		  Connection con = ConnectionUtil.getConnectionFromFile("C:/gitrepos/Bank/project_zero/src/main/java/resources/Connection.prop");
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
		try ( Connection con = ConnectionUtil.getConnectionFromFile("C:/gitrepos/Bank/project_zero/src/main/java/resources/Connection.prop")) {
			
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
						    
						}
		      }

		
	}

	
}
