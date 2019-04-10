package com.revature.beans;

public class Customer {
	private static int id = 0;
	private String fname;
	private String lname;
	private String username;
	private String password;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
		id = id++;
	}
	public Customer(int id) {
		super();
		
	}
	public Customer( String fname, String lname, String username, String password) {
		super();
		//Customer.id = id;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
	}
	
	public int getId() {
		return Customer.id;
	}
	
	public void setId(int id) {
		Customer.id = id;
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
	@Override
	public String toString() {
		return "Customer [id=" + id + ", First Name=" + fname + ", Last Name=" + lname + ", Username=" + username + ", password=" + password +"]";
	}
	
	

}
