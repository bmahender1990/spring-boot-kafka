package com.mahe.springkafkaproducer.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Customer {
	@NotBlank(message = "customerNumber is mandatory")
	@Size(min = 5, max = 50, message = "CustomerNumber should have 5 to 50 characters")
	@Pattern(regexp = "^[A-Za-z0-9]+$", message="CustomerNumber should be alphanumeric")
	private String customerNumber;
	
	@NotBlank(message = "firstName is mandatory")
    @Size(min = 10, max = 50, message = "firstName should have 10 to 50 characters")
	private String firstName;
	
	@NotBlank(message = "lastName is mandatory")
    @Size(min = 10, max = 50, message = "lastName should have 10 to 50 characters")
	private String lastName;
	
	@NotBlank(message = "birthdate is mandatory")
	@Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}", message="birthdate should be in DD-MM-YYYY format")
	private String birthdate;
	
	@NotBlank(message = "country is mandatory")
	private String country;
	
	@NotBlank(message = "countryCode is mandatory")
	@Size(max = 2, message = "countryCode should have maximum 2 characters")
	private String countryCode;
	
	@NotBlank(message = "mobileNumber is mandatory")
	@Size(max = 10, message = "mobileNumber should have maximum 2 characters")
	private String mobileNumber;
	
	@Email
	@NotBlank(message = "email is mandatory")
	@Size(max = 50, message = "email should have maximum 50 characters")
	private String email;
	
	@NotBlank(message = "customerStatus is mandatory")	
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
