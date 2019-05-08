package com.videos.model;

import com.videos.tools.Tools;

public class User {
	private String userName;
	private String name;
	private String lastname;
	private String password;
	private String registrationDate;

	public User() {
		super();
	}

	public User(String userName) {
		this.userName = userName;
	}

	public User(String userName, String name, String lastname,
			String password) {
		this.userName = userName;
		this.name = name;
		this.lastname = lastname;
		this.password = password;
		this.registrationDate = Tools.getDate();
	}
	
	public User(String userName, String name, String lastname,
			String password, String registrationDate) {
		this.userName = userName;
		this.name = name;
		this.lastname = lastname;
		this.password = password;
		this.registrationDate = registrationDate;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getLastname() {
		return lastname;
	}

	public final void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public final String getUserName() {
		return userName;
	}

	public final String getRegistrationDate() {
		return registrationDate;
	}
	
	public boolean equals(User otherUser) {
		return this.getUserName().equals(otherUser.getUserName())
				&& (this.getName().equals(otherUser.getName()))
				&& (this.getLastname().equals(otherUser.getLastname()))
				&& (this.getPassword().equals(otherUser.getPassword()))
				&& (this.getRegistrationDate()
						.equals(otherUser.registrationDate));
	}
	
	public String toString() {
		return "\nUsername: " + this.getUserName() + "\nName: " + this.getName()
				+ "\nLastname: " + this.getLastname() + "\nPassword: "
				+ this.getPassword() + "\nRegistration date: "
				+ this.getRegistrationDate();
	}

}
