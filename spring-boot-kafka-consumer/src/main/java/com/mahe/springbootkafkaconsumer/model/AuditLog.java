package com.mahe.springbootkafkaconsumer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name = "audit_log")
@Table(name = "audit_log")
public class AuditLog {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int ID;
	@Column
	private String CUSTOMER_NUMBER;
	@Column(length=1000)
	private String PAYLOAD;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getCUSTOMER_NUMBER() {
		return CUSTOMER_NUMBER;
	}
	public void setCUSTOMER_NUMBER(String cUSTOMER_NUMBER) {
		CUSTOMER_NUMBER = cUSTOMER_NUMBER;
	}
	public String getPAYLOAD() {
		return PAYLOAD;
	}
	public void setPAYLOAD(String pAYLOAD) {
		PAYLOAD = pAYLOAD;
	}
	

}
