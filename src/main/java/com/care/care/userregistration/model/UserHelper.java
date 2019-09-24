package com.care.care.userregistration.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class UserHelper {
	private String name,password,email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dueDate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
}
