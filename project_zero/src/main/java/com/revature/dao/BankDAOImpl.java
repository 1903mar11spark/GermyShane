package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.revature.util.*;
import com.revature.beans.*;
public class BankDAOImpl implements BankDAO {
	
	public List<Customer> getCustomer(){
		
		return null;
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
			             }
		      }
		
	}
	public void updateCust(Customer bear) {
		
	}
	public void deleteCust(Customer bear) {
		
	}

}
