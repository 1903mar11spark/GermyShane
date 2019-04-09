package com.revature.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.*;
import com.revature.dao.*;
import com.revature.util.ConnectionUtil;

public class BankDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 File file=new File("C:/gitrepos/Bank/project_zero/src/main/java/resources/Connection.prop");
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
		
		  Connection con = ConnectionUtil.getConnectionFromFile("C:/gitrepos/Bank/project_zero/src/main/java/resources/Connection.prop");
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
		  
		  Scanner in = new Scanner(System.in);
		  Customer a = new Customer();
		  Account b = new Account();
		  int choice;
		  
		  menu();
		  System.out.println("What would you like to do?:");
		  choice = in.nextInt();
		  do {
			  
			 switch (choice) {
			 
			 case 1 :
				 System.out.println("hi");
				 break;
			 case 2 :
				 break;
			 case 3 :
				 break;
			 case 4 :
				 
				 break;
			 case 5 :
				 menu();
				 break;
			 case 6 :
				 break;
				 
			default:
				break;
			 }
			 
			System.out.println("What would you like to do?:");
			choice = in.nextInt();
			
		  }while(choice != 6);
		 
	}
	public static void CreateBankUser() {
		String first, last, user, pass;
		Scanner in = new Scanner(System.in);
		
		
		System.out.println("Thank you for choosing ShaneCorp Bank Inc!");
		
	
		System.out.println("To create a new user account I would like to ask you your first and last name. Click enter after each entry.");
		first = in.toString();
		last = in.toString();
		if( first.isEmpty() || last.isEmpty() ) {
			do { 
				System.out.println("Error! You must enter a First AND Last name! Please try again."
						+ "Click enter after each entry.");
				first = in.toString();
				last = in.toString();
			}while( first.isEmpty() && last.isEmpty() );
		}
		System.out.println("Thank you for. Now to create your account.");
		System.out.print("Please enter your desired username: ");
		user = in.toString();
		System.out.println("Now enter your desired password: ");
		pass = in.toString();
		
		if( user.isEmpty() || pass.isEmpty() ) {
			do { 
				System.out.println("Error! You must enter a valid username and password! Please try again."
						+ "Click enter after each entry.");
				user = in.toString();
				pass = in.toString();
			}while( user.isEmpty() && pass.isEmpty() );
		}
		Customer create = new Customer(first,last,user, pass);
		BankDAOImpl bank= new BankDAOImpl();
		bank.createCust(create);
	}
	public static void menu() {
		System.out.println("Welcome to ShaneCorp Bank Inc!\n");
		System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. View Account Balance");
        System.out.println("4. Create User");
        System.out.println("5. Menu");
        System.out.println("6. Exit\n");
	}
}
