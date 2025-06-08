package com.ajay.booking;

public class User {
	private String userId;
	private String password;
	
	
	
	public User() {
		System.out.println("User Param Constructor.");
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
