package com.careem.kmsandmore.data;

import org.springframework.data.annotation.Id;

public class User {

	@Id
	private String id;

	private String name;

	private String email;
	
	private CareemWallet wallet;

	public User() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public CareemWallet getWallet() {
		return wallet;
	}
	
	public void setWallet(CareemWallet wallet) {
		this.wallet = wallet;
	}
	
	@Override
	public String toString() {
		return getId() + " - " + getName();
	}

}
