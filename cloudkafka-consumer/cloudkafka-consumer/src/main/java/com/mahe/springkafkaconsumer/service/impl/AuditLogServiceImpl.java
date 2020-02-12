package com.mahe.springkafkaconsumer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahe.springkafkaconsumer.domain.AuditLog;
import com.mahe.springkafkaconsumer.domain.Customer;
import com.mahe.springkafkaconsumer.repository.AuditLogRepository;
import com.mahe.springkafkaconsumer.service.AuditLogService;


@Service
public class AuditLogServiceImpl implements AuditLogService {
  @Autowired
  private AuditLogRepository auditLogRepository;



  @Override
  public void save(String payload) throws JsonProcessingException {
    Customer customer =
        new ObjectMapper().enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
            .readValue(payload, Customer.class);

    AuditLog auditLog = new AuditLog();
    auditLog.setCustomerNumber(customer.getCustomerNumber());
    auditLog.setPayload(payload);

    auditLogRepository.saveAndFlush(auditLog);

  }

}
