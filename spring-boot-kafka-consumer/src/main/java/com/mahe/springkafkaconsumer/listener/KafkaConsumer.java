package com.mahe.springkafkaconsumer.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahe.springkafkaconsumer.model.AuditLog;
import com.mahe.springkafkaconsumer.model.Customer;
import com.mahe.springkafkaconsumer.model.ErrorLog;
import com.mahe.springkafkaconsumer.service.ConsumerService;


@Service
public class KafkaConsumer {
	private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);
	
	@Autowired(required=true)
	private ConsumerService service;
	
	@KafkaListener(topics="KafkaDemo",groupId="group_id")
	public void consume(ConsumerRecord message) {
			
		LOG.info("consume data='{}' to topic='{}'", message.value(), message.topic());
		String customerStr=(String) message.value();

        ObjectMapper objectMapper = new ObjectMapper();        
        try {
			Customer customer = objectMapper.readValue(customerStr, Customer.class);			
			
			service.addCustomer(customer);
			
			AuditLog audit =new AuditLog();			
			audit.setCUSTOMER_NUMBER(customer.getCustomerNumber());
			audit.setPAYLOAD(customerStr);
			service.addAudit(audit);
			
		}  catch (Exception e) {
			ErrorLog error= new ErrorLog(e.getLocalizedMessage(), e.getMessage(), customerStr);
			service.addError(error);
		}
		
	}

}
