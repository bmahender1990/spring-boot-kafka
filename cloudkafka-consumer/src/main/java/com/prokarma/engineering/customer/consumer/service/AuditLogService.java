package com.prokarma.engineering.customer.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface AuditLogService {

  void save(String payload) throws JsonProcessingException;

}
