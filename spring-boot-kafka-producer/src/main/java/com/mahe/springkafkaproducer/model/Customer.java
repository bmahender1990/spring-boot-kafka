package com.mahe.springkafkaproducer.model;

public class Customer {
	
	private String customerNumber;
	private String firstName;
	private String lastName;
	private String birthdate;
	private String country;
	private String countryCode;
	private String mobileNumber;
	private String email;
	private String customerStatus;
	private Address address;
		
	public Customer() {};
	
	public Customer(String customerNumber, String firstName, String lastName, String birthdate, String country,
			String countryCode, String mobileNumber, String email, String customerStatus, Address address) {
		super();
		this.customerNumber = customerNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.country = country;
		this.countryCode = countryCode;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.customerStatus = customerStatus;
		this.address = address;
	}
	
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCustomerStatus() {
		return customerStatus;
	}
	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	

}
