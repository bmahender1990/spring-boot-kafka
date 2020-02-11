package com.mahe.springkafka.customer.publisher.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.mahe.springkafka.customer.publisher.model.Customer;

@Component
public class KafkaProducerService {

  private final KafkaTemplate<String, Customer> kafkaTemplate;

  @Value("${cloudkarafka.topic}")
  private String topic;

  KafkaProducerService(KafkaTemplate<String, Customer> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void send(Customer message) {
    this.kafkaTemplate.send(topic, message);
    System.out.println("Sent sample message [" + message + "] to " + topic);
  }

}
