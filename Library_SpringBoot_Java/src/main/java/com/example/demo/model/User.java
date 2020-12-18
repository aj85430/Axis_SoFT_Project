package com.example.demo.model;


import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	private int userId;
	
	private String userName;

	private String email;
	
	public User() {}
	
	public User(int userId, String userName, String email) {
		super();
		this.userId = userId;
		this.userName = userName;
		
		this.email = email;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
