package com.revature.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.*;
import com.revature.dao.*;
import com.revature.util.ConnectionUtil;

public class BankDriver {

	public static void main(String[] args) {

		//Checking if file cane be found. Returns true if so, else false.
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
		  
		  //Create connection with user. Do some exception handling.
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
		  
		  //Instantiate Scanner, Customer and Account objects to use later. Create variable of type int for switch statement.
		  Scanner in = new Scanner(System.in);
		  Customer a = new Customer();
		  Account b = new Account();
		  int choice;
		  
		  //Print menu for user selection. Prompt user for action they would like to do.
		  menu();
		  System.out.println("How can we assist you today? Enter a number between 1 and 6:");
		  choice = in.nextInt();
		  
		  //A do while loop for the menu. Exits when user enters the number 6. Offers the user 5 other choices.
		  do {
			  
			 switch (choice) {
			 
			 case 1 :
				 Deposit();
				 break;
				 
			 case 2 :
				 break;
				 
			 case 3 :
				 break;
				 
			 case 4 :
				CreateBankUser();
				 break;
				 
			 case 5 :
				 menu();
				 break;
				 
			 case 6 :
				 break;
				 
			default:
				break;
			 }
			 
			//After user makes selection and  finishes their task, they are prompted again for the next action.
			System.out.println("What would you like to do?: ");
			choice = in.nextInt();
			
		  }while(choice != 6);
		 
	}
	//Option 4 - Create user and add to table.
	public static void CreateBankUser() {
	
		//Strings that will hold the users first name, last name, user name and password.
		String first, last, user, pass;
		Scanner in = new Scanner(System.in);
		
		
		System.out.println("Thank you for choosing ShaneCorp Bank Inc!");
		
	
		System.out.println("To create a new user account I would like to ask you your first and last name. Click enter after each entry.");
		first = in.nextLine();
		last = in.nextLine();
		
		if( first.isEmpty() || last.isEmpty() ) {
			
			do { 
				
				System.out.println("Error! You must enter a First AND Last name! Please try again."
						+ "Click enter after each entry.");
				first = in.nextLine();
				last = in.nextLine();
				
			}while( first.isEmpty() && last.isEmpty() );
			
		}
		
		/*If user enters values for their first an last name, they are then prompted to create their user name and password.
		Minimum validation for their user name and password as well.*/
		System.out.println("Thank you for. Now to create your account.");
		System.out.print("Please enter your desired username: ");
		user = in.nextLine();
		
		System.out.print("Now enter your desired password: ");
		pass = in.nextLine();
		
		if( user.isEmpty() || pass.isEmpty() ) {
			
			do { 
				
				System.out.println("Error! You must enter a valid username and password! Please try again."
						+ "Click enter after each entry.");
				user = in.nextLine();
				pass = in.nextLine();
				
			}while( user.isEmpty() && pass.isEmpty() );
		}
		
		//Take user input and create the user object. Pass Customer object into BankDAOImpl which implements the BankDAO interface.
		Customer create = new Customer(first,last,user, pass);
		BankDAOImpl bank= new BankDAOImpl();
		bank.createCust(create);
		
	}
	//Menu printed out in main.
	public static void menu() {
		System.out.println("Welcome to ShaneCorp Bank Inc!\n");
		System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. View Account Balance");
        System.out.println("4. Create User");
        System.out.println("5. Menu");
        System.out.println("6. Exit\n");
	}
	
	public static void Deposit() {
		Scanner in = new Scanner(System.in);
		NumberFormat form = NumberFormat.getCurrencyInstance();
		String input = "nada";
		double money;
		
		System.out.println("To deposit money, please specify the amount of which you would like to deposit below.");
		do{
		   
				System.out.print("Enter deposit amount: ");
		        input = in.next();
		        
		        if(!input.matches("[0-9.]*")){
		        	System.out.println("Sorry that is not a valid entry. Please try again. ");
		        }
		   
		}while(!input.matches("[0-9.]*"));
		money = Double.parseDouble(input);
		
		
		
		
		
		System.out.println("Thank you for your deposit of " + form.format(money) +"!");
		
	}
	
	public static void Withdraw() {
		Scanner in = new Scanner(System.in);
		NumberFormat form = NumberFormat.getCurrencyInstance();
		String input = "nada";
		double money;
		
		System.out.println("To withdraw money, please specify the amount of which you would like to withdraw below.");
		do{
		   
				System.out.print("Enter withdrawal amount: ");
		        input = in.next();
		        
		        if(!input.matches("[0-9.]*")){
		        	System.out.println("Sorry that is not a valid entry. Please try again. ");
		        }
		   
		}while(!input.matches("[0-9.]*"));
		money = Double.parseDouble(input);
		
		
		
		
		
		System.out.println("You withdrew from your account in the amount of " + form.format(money) +". Thank you, come again!");
	}
	
	public static void getBal() {
	
	}
}
