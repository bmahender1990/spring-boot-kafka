package com.mahe.springkafka.customer.publisher.service;

import com.mahe.springkafka.customer.publisher.model.Customer;

public interface KafkaProducerService {

  void send(Customer customer);
}
