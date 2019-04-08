package com.revature.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.*;
import com.revature.dao.*;
import com.revature.util.ConnectionUtil;

public class BankDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
///Bank/project_zero/src/main/java/resources/Connection.prop
		 File file=new File("//Users//shaneflynn//GitRepos//Bank//project_zero//src//main//java//resources//Connection.prop"); 
		 System.out.println(new File(".").getAbsoluteFile());
		    System.out.println(file.exists());
		    try {
				Scanner scan = new Scanner(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  try 
		  { 
		  Connection con = ConnectionUtil.getConnectionFromFile("//Users//shaneflynn//GitRepos//Bank//project_zero//src//main//java//resources//Connection.prop");
		  System.out.println(con); 
		  }
		  catch (SQLException e) 
		  { 
			  e.printStackTrace(); 
		  }
		  catch (IOException e) 
		  {
			  e.printStackTrace(); 
		  }
		  
		  BankDAO bd = new BankDAOImpl();
			List<Customer> customerList = bd.getCustomer();
			for(Customer b : customerList) {
				System.out.println(b);
	}
	}

}
