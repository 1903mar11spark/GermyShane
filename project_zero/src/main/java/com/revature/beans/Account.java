package com.revature.beans;

public class Account {
	private int accId;
	private Customer user;
	private double balance = 0;
	private String type = "Checkings";
	
	public Account() {
		super();
	}
	public Account(Customer user) {
		this.user = user;
	}
	public Account(Customer user, double balance, String type) {
		super();
		this.user = user;
		this.balance = balance;
		this.type = type;
	}
	
	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public Customer getUser() {
		return this.user;
	}

	public void setUser(Customer user) {
		this.user = user;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Account [id=" + this.accId + ", First Name=" + this.user.getFname() + ", Last Name=" + this.user.getLname() + ", Username=" + this.user.getUsername() + ", password=" + this.user.getPassword() +"]";
	}

	

}
