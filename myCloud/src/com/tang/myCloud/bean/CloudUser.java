package com.tang.myCloud.bean;

import org.springframework.stereotype.Repository;

@Repository("user")
public class CloudUser {
	private long userId;
	private String userName;
	private String userPassword;
	private char isvip;

	public CloudUser() {
		// TODO Auto-generated constructor stub
	}

	public CloudUser(String userName, String userPassword) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
	}

	public CloudUser(String userName, String userPassword, char isvip) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.isvip = isvip;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public char getIsvip() {
		return isvip;
	}

	public void setIsvip(char isvip) {
		this.isvip = isvip;
	}

}
