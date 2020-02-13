package com.prokarma.engineering.customer.publisher.service;

import com.prokarma.engineering.customer.publisher.model.Customer;

public interface KafkaProducerService {

  void send(Customer customer);
}
