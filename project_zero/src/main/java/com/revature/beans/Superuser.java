package com.revature.beans;
public class Superuser {
	private int id;
	private String fname;
	private String lname;
	private String username;
	private String password;
	private int accId;
	private double balance;
	private String type;


public Superuser( int id, String fname, String lname, String username, String password, int accId, double balance, String type) {
	super();
	this.id = id;
	this.fname = fname;
	this.lname = lname;
	this.username = username;
	this.password = password;
	this.accId = accId;
	this.balance = balance;
	this.type = type;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getFname() {
	return fname;
}

public void setFname(String fname) {
	this.fname = fname;
}

public String getLname() {
	return lname;
}

public void setLname(String lname) {
	this.lname = lname;
}
public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}
public int getAccId() {
	return accId;
}

public void setAccId(int accId) {
	this.accId = accId;
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
	return "Superuser [id=" + id + ", First Name=" + fname + ", Last Name=" + lname + ", Username=" + username + ", Password=" + password + ",AccountId=" + accId+ ", Balance=" + balance + ", Type=" + type +"]";
}
}
	
	
	
	