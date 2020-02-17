package com.prokarma.engineering.customer.consumer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prokarma.engineering.customer.consumer.configuration.JsonConfiguration;
import com.prokarma.engineering.customer.consumer.domain.AuditLog;
import com.prokarma.engineering.customer.consumer.domain.Customer;
import com.prokarma.engineering.customer.consumer.repository.AuditLogRepository;
import com.prokarma.engineering.customer.consumer.service.AuditLogService;


@Service
public class AuditLogServiceImpl implements AuditLogService {
  @Autowired
  private AuditLogRepository auditLogRepository;

  @Autowired
  private JsonConfiguration jsonConfiguration;

  @Override
  public void save(String payload) {
    Customer customer = jsonConfiguration.jsonStringToObjectConverter(payload);

    AuditLog auditLog = new AuditLog();
    auditLog.setCustomerNumber(customer.getCustomerNumber());
    auditLog.setPayload(payload);

    auditLogRepository.saveAndFlush(auditLog);
  }

}
