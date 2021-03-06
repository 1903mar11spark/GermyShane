package com.revature.beans;

import java.text.NumberFormat;

public class Transactions {
	private int accID;
	private int tID;
	String timestamp;
	private double amount;
	private double balance;
	

	public Transactions() {
		super();
	}


	public Transactions(int accID, int tID, String timestamp, double amount, double balance) {
		super();
		this.accID = accID;
		this.tID = tID;
		this.timestamp = timestamp;
		this.amount = amount;
		this.balance = balance;
	}
	
	public Transactions(int accID, double amount, double balance) {
		super();
		this.accID = accID;
		this.amount = amount;
		this.balance = balance;
	}

	public int getAccID() {
		return accID;
	}

	public void setAccID(int accID) {
		this.accID = accID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int gettID() {
		return tID;
	}

	public void settID(int tID) {
		this.tID = tID;
	}

	public String getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		NumberFormat form = NumberFormat.getCurrencyInstance();
		return "Transactions [ Account ID: " + accID +", Transaction ID: " + tID + ","
				+ " Transaction Amount " + form.format(amount) + ", Account Balance:" + form.format(balance) + ", Time:" + timestamp + "]";
	}
}
