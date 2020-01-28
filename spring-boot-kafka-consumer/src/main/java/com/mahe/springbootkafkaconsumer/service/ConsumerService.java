package com.mahe.springbootkafkaconsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahe.springbootkafkaconsumer.dao.AuditRepository;
import com.mahe.springbootkafkaconsumer.dao.ConsumerRepository;
import com.mahe.springbootkafkaconsumer.dao.ErrorRepository;
import com.mahe.springbootkafkaconsumer.model.AuditLog;
import com.mahe.springbootkafkaconsumer.model.Customer;
import com.mahe.springbootkafkaconsumer.model.ErrorLog;

@Service
public class ConsumerService {

	@Autowired
	ConsumerRepository conRepo;
	@Autowired
	AuditRepository auditRepo;
	@Autowired
	ErrorRepository errorRepo;
	
	public void addAudit(AuditLog audit) {
		auditRepo.save(audit);
	}
	
	public void addCustomer(Customer customer) {
		conRepo.save(customer);
	}
	
	public void addError(ErrorLog errorlog) {
		errorRepo.save(errorlog);
	}	
	
}
