package com.mahe.springkafkaconsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahe.springkafkaconsumer.dao.AuditRepository;
import com.mahe.springkafkaconsumer.dao.ConsumerRepository;
import com.mahe.springkafkaconsumer.dao.ErrorRepository;
import com.mahe.springkafkaconsumer.model.AuditLog;
import com.mahe.springkafkaconsumer.model.Customer;
import com.mahe.springkafkaconsumer.model.ErrorLog;

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
