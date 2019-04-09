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
		
		//Connection con = null;
        Statement stmt = null;
        
		try ( Connection con = ConnectionUtil.getConnectionFromFile("C:/gitrepos/Bank/project_zero/src/main/java/resources/Connection.prop")) {
		
			String sql = "INSERT INTO BANKUSER (USER_ID, USERNAME, PASSWORD, FIRSTNAME ,LASTNAME )" + 
					"VALUES ("+user.getId()+","+ user.getUsername() +", "+user.getPassword()+", "+user.getFname()+", "+user.getLname()+")" ;
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getInt("USER_ID"));
				System.out.println(rs.getString("USERNAME"));
				System.out.println(rs.getString("PASSWORD"));
				System.out.println(rs.getString("FIRSTNAME"));
				System.out.println(rs.getString("LASTNAME"));
			}
			
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
				finally {
		            try {
		                   stmt.close();
		                  // con.close();
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
