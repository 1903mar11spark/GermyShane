package com.revature.beans;

public class Customer {
	private int id;
	private String fname;
	private String lname;
	private String username;
	private String password;
	
	public Customer() {
		super();
	
	}
	public Customer(String fname, String lname, String username, String password) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
	}
	
	public Customer(int id, String fname, String lname, String username, String password) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
	}
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFname() {
		return this.fname;
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
	@Override
	public String toString() {
		return "Customer [id = " + this.id + ", First Name = " + this.fname + ", Last Name = " + this.lname + ", Username = " 
				+ this.username + ", password = " + this.password +"]";
	}

	
	

}
