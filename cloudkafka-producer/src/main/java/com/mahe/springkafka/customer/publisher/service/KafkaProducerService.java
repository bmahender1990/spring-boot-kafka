package com.mahe.springkafka.customer.publisher.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.mahe.springkafka.customer.publisher.model.Customer;

@Component
public class KafkaProducerService {

  private static final Logger LOGGER = LogManager.getLogger(KafkaProducerService.class);

  @Value("${cloudkarafka.topic}")
  private String topic;

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private Gson jsonConverter;

  public void send(Customer customer) {

    String customerJson = jsonConverter.toJson(customer);
    kafkaTemplate.send(topic, customerJson).addCallback(
        result -> LOGGER.info(String.format("Sent message=[%s] with offset=[%s]", customerJson,
            result.getRecordMetadata().offset())),
        ex -> LOGGER.error(String.format("Unable to send message=[%s] due to : %s", customerJson,
            ex.getMessage())));

  }

}
