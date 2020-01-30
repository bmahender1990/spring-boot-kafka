package com.mahe.springkafkaconsumer.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "customer_data")
@Table(name = "customer_data")
public class Customer {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int customerId;
	private String customerNumber;
	private String firstName;
	private String lastName;
	@Type(type="date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "DD-MM-YYYY")
	private Date birthdate;
	private String country;
	private String countryCode;
	private String mobileNumber;
	private String email;
	private String customerStatus;
	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;
		
	public Customer() {};
	
	public Customer(String customerNumber, String firstName, String lastName, Date birthdate, String country,
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
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
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
