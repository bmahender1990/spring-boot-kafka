package com.mahe.springkafkaproducer.model;

public class Address {
	private String addressLine1;
	private String addressLine2;
	private String street;
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
