package com.mahe.springkafkaproducer.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Address {
	@NotBlank(message = "addressLine1 is mandatory")
	@Size(max = 50, message = "addressLine1 should have maximum 50 characters")
	private String addressLine1;
	private String addressLine2;
	private String street;
	
	@NotBlank(message = "postalCode is mandatory")
	@Size(max = 5, message = "postalCode should have maximum 5 characters")
	private String postalCode;
	
		
	public Address() {	}

	public Address(String addressLine1, String addressLine2, String street, String postalCode) {		
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.street = street;
		this.postalCode = postalCode;
	}
	
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	
	
	

}
