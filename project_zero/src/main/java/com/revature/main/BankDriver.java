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
