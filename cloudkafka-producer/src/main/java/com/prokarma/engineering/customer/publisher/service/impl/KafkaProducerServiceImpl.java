package com.prokarma.engineering.customer.publisher.service.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.prokarma.engineering.customer.publisher.model.Customer;
import com.prokarma.engineering.customer.publisher.service.KafkaProducerService;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

  private static final Logger LOGGER = LogManager.getLogger(KafkaProducerServiceImpl.class);

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
