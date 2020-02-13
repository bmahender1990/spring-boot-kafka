package com.prokarma.engineering.customer.consumer.service;

public interface ErrorLogService {

  void save(Exception exception, String payload);
}
