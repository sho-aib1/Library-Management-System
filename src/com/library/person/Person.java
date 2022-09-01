package com.library.person;

import java.io.Serializable;
import java.util.regex.Pattern;

abstract public class Person implements Serializable {
	protected String name;
	protected String emailId;
	protected String phoneNumber;
	protected String address;
	protected String dob;

	public Person(String name, String emailId, String phoneNumber, String address, String dob) {
		super();
		this.setName(name);
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.setDob(dob);
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", emailId=" + emailId + ", phoneNumber=" + phoneNumber + ", address=" + address
				+ ", dob=" + dob + "]";
	}

	public Person() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		boolean isvalidName = Pattern.matches("[a-zA-z]+", name);
		if (isvalidName) {
			this.name = name;
		} else {
			this.name = "default name";
		}

	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDob() {

		return dob;
	}

	public void setDob(String dob) {
		boolean isvalidDob = Pattern.matches("\\d{2}-\\d{2}-\\d{4}", dob);
		if (isvalidDob)
			this.dob = dob;
		else {
			this.dob = "1-06-2005";
		}
	}

}
