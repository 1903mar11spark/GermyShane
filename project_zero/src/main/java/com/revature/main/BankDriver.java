package com.revature.main;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Scanner;

import com.revature.beans.*;
import com.revature.dao.*;
import com.revature.util.ConnectionUtil;

public class BankDriver {

	public static void main(String[] args) {

		
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
		  Customer a = new Customer() ;
		  BankDAO bd = new BankDAOImpl();
		  NumberFormat form = NumberFormat.getCurrencyInstance();
		  String first, last, user =  "" , pass = "";
		  int choice;
		  
		  System.out.println("Welcome to ShaneCorp Bank! If you have an account enter 1, if you need to create an account enter 2");
		  String num = in.nextLine();
		  
		  //if.1 Beginning
		  if (num.contentEquals("1")) {
			  
					  //Scanner user = new Scanner(System.in);
					  System.out.println("Input a username.");
					  user = in.nextLine();
					  System.out.println("Input a Password.");	
					  pass = in.nextLine();
					  
					  if(user.isEmpty() || pass.isEmpty()) {
						  do { 
							  System.out.println("Error! You must enter a First AND Last name! Please try again.");
							  System.out.println("Input a username.");
							  user = in.nextLine();
							  System.out.println("Input a Password.");	
							  pass = in.nextLine();
								
								}while( user.isEmpty() && pass.isEmpty() );
					  }
						//If.2 Begins
						  if (!bd.getCustByLogin(user, pass)) {
							  do { 
								  System.out.println("This log in does not exist. Please try again or enter 0 to quit.");
								  System.out.println("Input a username.");
								  user = in.nextLine();
								  System.out.println("Input a Password.");	
								  pass = in.nextLine();
							  	
								  if(user == "0" || pass == "0") {System.out.println("Goodbye!"); System.exit(0);}
									
							  }while( user.isEmpty() && pass.isEmpty()  || user == "0" || pass == "0");
						  }
			  
			//if.1 Ends, else if .1 Begins  
		  } else if (num.contentEquals("2")) {
				
				//Strings that will hold the users first name, last name, user name and password.				
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
				
				System.out.println("Thank you. Now you must create your user login.");
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
				Customer cust = new Customer(first,last,user, pass);
				a = cust;
				bd.createCust(a);
				System.out.println("Your user account have been created.\n");
				
		  }//else if.1 Ends
		  
		  if(user == "0" || pass == "0") {System.out.println("Goodbye!"); System.exit(0);}
			  
		  Customer theCust = bd.getCustomer(user,pass);
		  if (bd.getCustByLogin(user, pass)) {
		  //Print menu for user selection. Prompt user for action they would like to do.
		  menu();
		  System.out.println("How can we assist you today? Enter a number between 1 and 6:");
		  choice = in.nextInt();
		  
		  //A do while loop for the menu. Exits when user enters the number 6. Offers the user 5 other choices.
		  do {
			  
				 switch (choice) {
				 //1. Deposit
				 case 1 :
						String deposit = "nada";
						double dmoney, newMonay;
						
						System.out.println("To deposit money, please specify the amount of which you would like to deposit below.");
						do{
						   
								System.out.print("Enter deposit amount: ");
						        deposit = in.next();
						        
						        if(!deposit.matches("[0-9.]*")){
						        	System.out.println("Sorry that is not a valid entry. Please try again. ");
						        }
						   
						}while(!deposit.matches("[0-9.]*"));
						dmoney = Double.parseDouble(deposit);
					
						
						newMonay = bd.Deposit(theCust, dmoney);
						System.out.println("You deposited " + form.format(dmoney) +" into yout account" +". Your current balance is "+ form.format(newMonay) + "!\n");
					 break;
				//2. Withdraw 
				 case 2 :
					 	String withdraw = "nada";
						double wmoney, newMoney;
						
						System.out.println("To deposit money, please specify the amount of which you would like to deposit below.");
						do{
						   
								System.out.print("Enter deposit amount: ");
						        withdraw = in.next();
						        
						        if(!withdraw.matches("[0-9.]*")){
						        	System.out.println("Sorry that is not a valid entry. Numbers only. Please try again. ");
						        }
						   
						}while(!withdraw.matches("[0-9.]*"));
						wmoney = Double.parseDouble(withdraw);
						
						newMoney = bd.Withdraw(theCust, wmoney);
						System.out.println("You have withdrew " + form.format(wmoney) +". Your current balance is "+ form.format(newMoney) + "!\n");
					 
					 break;
				//3. View Checkings or Savings	
				 case 3 :
					 double bal = 0;
					 int myAccount;
					 String checkorsave = "";
					 System.out.println("From which account balance whould you like to see? Enter 1 for Checkings, 2 for Savings, or 0 to exit. ");
					 myAccount = in.nextInt();
					 while(myAccount != 1 || myAccount != 2 || myAccount!= 0) {
						 
						 System.out.println("Please enter a valid entry. Enter 1 for Checkings, 2 for Savings, or 0 to exit.");
						 myAccount = in.nextInt();
						 if (myAccount == 1) {
							 checkorsave = "Checkings";
						 }else if (myAccount == 2) {
							 checkorsave = "Savings";
						 }
					 }
					 if (myAccount == 1) {
						 checkorsave = "Checkings";
					 }else if (myAccount == 2) {
						 checkorsave = "Savings";
					 }
					 bal = bd.getMoney(theCust, checkorsave);
					 if(myAccount != 0 || bal != -404) {
					 System.out.println("You have " + form.format(bal) + "in");
					 }
					 
					 menu();
					 break;
					 
				 case 4 :
					 	String type = "";
					 	int input = 0;
					 	
					 	System.out.println("Thank you for choosing ShaneCorp Bank Inc!");
						System.out.println("To create a new bank account you must first choose which type of account you want to open.");
						System.out.println("Enter 1 for checkings or 2 for Savings. Enter 0 to quit:");
						String typeacc = in.next();
						
						while(!typeacc.matches("[0-2.]*")){
					        	System.out.println("Sorry that is not a valid entry. Please try again. ");
					        	typeacc = in.next();
					        }
						
								switch (typeacc) {
								
								case "1":
									type = "Checkings";
									input = theCust.getId();
									bd.createAcc(type, input);
									break;
									
								case "2":
									type = "Savings";
									input = theCust.getId();
									bd.createAcc(type, input);
									break;
									
								case "0":
									break;
									
								default:
									break;
								}
						System.out.println("Thank you for choosing ShaneCorp Inc. for your banking services!");
						menu();
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
		 in.close();
}

//Menu printed out in main.
public static void menu() {
			
			System.out.println("Welcome to ShaneCorp Bank Inc!");
			System.out.println("Select an option from the menu below:\n");
			System.out.println("1. Deposit");
	        System.out.println("2. Withdraw");
	        System.out.println("3. View Account Balance");
	        System.out.println("4. Create Savings/Checkings Account");
	        System.out.println("5. Menu");
	        System.out.println("6. Exit\n");
	        
}


}
