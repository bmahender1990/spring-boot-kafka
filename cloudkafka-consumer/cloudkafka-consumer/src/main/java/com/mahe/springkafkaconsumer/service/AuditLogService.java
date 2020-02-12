package com.mahe.springkafkaconsumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface AuditLogService {

  void save(String payload) throws JsonProcessingException;

}
