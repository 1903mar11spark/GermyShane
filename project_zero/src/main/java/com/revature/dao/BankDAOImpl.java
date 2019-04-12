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
	
	String plug = "C:/gitrepos/Bank/project_zero/src/main/java/resources/Connection.prop";
	
	public List<Customer> getCustomer(){
		List<Customer> cust = new ArrayList<Customer>();
		Connection con = null;
		ResultSet rs = null;
		
		try 
		  { 
		   con = ConnectionUtil.getConnectionFromFile(plug);
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
	
	public List<Transactions>getTransactions(){
		List<Transactions> trans = new ArrayList<Transactions>();
		Connection con = null;
		ResultSet rs = null;
		Statement stmt = null;
		
		try 
		  { 
		   con = ConnectionUtil.getConnectionFromFile(plug);
		   String sql = "SELECT T_ID, ACCOUNT_ID, AMOUNT, TIME_STAMP, BALANCE FROM TRANSACTIONS";
		   stmt = con.createStatement();
		   rs = stmt.executeQuery(sql);
		   
				while (rs.next()) {
					int tID = rs.getInt("T_ID");
					int aID = rs.getInt("ACCOUNT_ID");
					double amount = rs.getDouble("AMOUNT");
					String time = rs.getString("TIME_STAMP");
					double balance = rs.getDouble("BALANCE");
					trans.add(new Transactions(tID, aID, time, amount, balance));
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
		
		return trans;
	}
	
	public void createCust(Customer user) {
		//Create PreparedStatement object to execute DML queries.
        PreparedStatement stmt = null;
       
        
        //Some exception handling with connecting to a file.
		try ( Connection con = ConnectionUtil.getConnectionFromFile(plug)) {
			
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
			
		  Connection con = ConnectionUtil.getConnectionFromFile(plug);
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
					
				try ( Connection con = ConnectionUtil.getConnectionFromFile(plug)) {
			
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
		
		try ( Connection con = ConnectionUtil.getConnectionFromFile(plug)) {
			
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
		int accID = 0;
		PreparedStatement stmt = null, stmt2 = null, stmt3 = null;
		
		ResultSet rs = null, rs2 = null;
		try ( Connection con = ConnectionUtil.getConnectionFromFile(plug)) {
			
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
				
				//Retrieving the account ID from this transaction to store in the Transactions table
				stmt3 = con.prepareStatement("SELECT ACCOUNT_ID FROM ACCOUNTS WHERE USER_ID = ? AND ACCOUNT_TYPE  = ?");
				stmt3.setInt(1, c.getId());
				stmt3.setString(2,type);
				rs = stmt3.executeQuery();
				
				while(rs.next()) {
					accID = rs.getInt("ACCOUNT_ID");
				}
			    
				newBal = money;
				storeTrans(accID,amount, newBal);
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
		int accID = 0;
		PreparedStatement stmt = null, stmt2 = null, stmt3 = null;
		
		ResultSet rs = null, rs2 = null;
		try ( Connection con = ConnectionUtil.getConnectionFromFile(plug)) {
			
			stmt = con.prepareStatement("SELECT BALANCE FROM ACCOUNTS WHERE USER_ID = ? AND ACCOUNT_TYPE = ?");
			stmt.setInt(1, c.getId());
			stmt.setString(2, type);
		    
		    rs = stmt.executeQuery();
		    //Retrieve balance from account if any
		    while(rs.next()) {
		    	money = rs.getDouble("BALANCE");
		    	error = money;
		    }
		    
		    //If no balance was retrieved then error was not reassigned, no balance exists of this type, program ends 
		    if(error == -404) {
			    System.out.println("You dont have a "+type+" account to deposit into.\n");
			    	return error;
				}
		    
		    	//If balance retrieval was successful, check to see if the account has enough money. No overdrafting
			    if(money >= amount) {
			    money = money -amount;
			    stmt2 = con.prepareStatement("UPDATE ACCOUNTS SET BALANCE = ? WHERE USER_ID = ? AND ACCOUNT_TYPE = ?");
				stmt2.setDouble(1, money);
				stmt2.setInt(2, c.getId());
				stmt2.setString(3, type);
				
				rs2 = stmt2.executeQuery();
				while(rs.next()) {
			    	money = rs.getDouble("BALANCE");
			    }
				
				//Retrieving the account ID from this transaction to store in the Transactions table
				stmt3 = con.prepareStatement("SELECT ACCOUNT_ID FROM ACCOUNTS WHERE USER_ID = ? AND ACCOUNT_TYPE  = ?");
				stmt3.setInt(1, c.getId());
				stmt3.setString(2,type);
				rs = stmt3.executeQuery();
				
				while(rs.next()) {
					accID = rs.getInt("ACCOUNT_ID");
				}
				newBal = money;
				
				storeTrans(accID, amount, newBal);
				    }else {
				    	System.out.println("You have less money than you wish to withdraw. We do not allow overdrafts, Sorry!");
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
				                    stmt2.close();
				                    stmt3.close();
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
		  con = ConnectionUtil.getConnectionFromFile(plug);
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

	@Override
	public double getBalanceById(int id) {
		PreparedStatement myStmt = null;
		double x = 0;
		ResultSet rs = null;
		try 
		  { 
		  Connection con = ConnectionUtil.getConnectionFromFile(plug);
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
			    //try { con.close(); } catch (Exception e) { /* ignored */ }
			}
		  return x;
}
	
	public void deleteSuper(int id) {
		double x = getBalanceById(id);
		if (x == 0) {
			CallableStatement stmt = null;
	        //Some exception handling with connecting to a file.
			try ( Connection con = ConnectionUtil.getConnectionFromFile(plug)) {
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
						   // try { con.close(); } catch (Exception e) { /* ignored */ }
						}
		      }
			
		} else if (x > 0){
			System.out.println("Cannot delete an account that has money in balance.");
		
		} else {
			CallableStatement stmt = null;
	        //Some exception handling with connecting to a file.
			try ( Connection con = ConnectionUtil.getConnectionFromFile(plug)) {
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
						    //try { con.close(); } catch (Exception e) { /* ignored */ }
						}
		      }
		}
			
}
	
	@Override
	public List<Superuser> getSuperuser() {
		List<Superuser> superU = new ArrayList<Superuser>();
		try 
		  { 
		  Connection con = ConnectionUtil.getConnectionFromFile(plug);
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
		try ( Connection con = ConnectionUtil.getConnectionFromFile(plug)) {
			
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

	@Override
	public void userDeletion(Customer begone) {
		double checking = 0, savings = 0 ;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try ( Connection con = ConnectionUtil.getConnectionFromFile(plug)) {
			
			stmt = con.prepareStatement("SELECT BALANCE FROM ACCOUNTS WHERE USER_ID = ? AND ACCOUNT_TYPE = ?");
			stmt.setInt(1, begone.getId());
			stmt.setString(2, "Checking");
		    rs = stmt.executeQuery();
		    
		    while(rs.next()) {
		    	checking = rs.getDouble("BALANCE");

		    	}
		    
		    stmt = con.prepareStatement("SELECT BALANCE FROM ACCOUNTS WHERE USER_ID = ? AND ACCOUNT_TYPE = ?");
			stmt.setInt(1, begone.getId());
			stmt.setString(2, "Savings");
		    rs = stmt.executeQuery();
		    
		    while(rs.next()) {
		    	savings = rs.getDouble("BALANCE");

		    	}
		    if(checking == 0 || savings == 0) {
		    	stmt = con.prepareStatement("DELETE FROM ACCOUNTS WHERE USER_ID = ?");
		    	stmt.setInt(1, begone.getId());
		    	stmt.executeUpdate();
		    	
		    }
		    
					}catch (SQLException sqlEx) {
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
	}

	public void storeTrans(int accID, double amount, double balance) {
		PreparedStatement stmt = null, stmt2 = null;
		ResultSet rs = null;
		
		try ( Connection con = ConnectionUtil.getConnectionFromFile(plug)) {
	    	
		    stmt2 = con.prepareStatement("INSERT INTO TRANSACTIONS (ACCOUNT_ID, AMOUNT, BALANCE) VALUES (?,?,?)");
		    stmt2.setInt(1,accID);
		    stmt2.setDouble(2,amount);
		    stmt2.setDouble(3,balance);
		    
		    stmt2.executeUpdate();
			    
           //More exception handling.
	      } catch (SQLException sqlEx) {
	             sqlEx.printStackTrace();
	             System.exit(1);  
	      } catch (IOException e1) {
			e1.printStackTrace();
			} finally {
		             try { 	//Ideally would close connection here.
		                    stmt2.close();  
			             } catch (Exception e) {
			                    System.exit(1); 
			             }
		             finally {
		     		    try { rs.close(); } catch (Exception e) { /* ignored */ }
		     		    //try { con.close(); } catch (Exception e) { /* ignored */ }
		      }
		
		
			}
	}
}
