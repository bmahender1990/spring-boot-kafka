package com.prokarma.engineering.customer.publisher.service.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.prokarma.engineering.customer.publisher.common.LogMaskingConverter;
import com.prokarma.engineering.customer.publisher.configuration.JsonConfiguration;
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
  private JsonConfiguration jsonConfiguration;

  @Autowired
  private LogMaskingConverter logMaskingConverter;

  public void send(Customer customer) {

    Customer customerMasked = logMaskingConverter.maskCustomer(customer);
    String customerJson = jsonConfiguration.jsonToStringConverter(customer);
    String customerJsonMasked = jsonConfiguration.jsonToStringConverter(customerMasked);


    kafkaTemplate.send(topic, customerJson).addCallback(
        result -> LOGGER.info(String.format("Sent message=[%s] with offset=[%s]",
            customerJsonMasked, result.getRecordMetadata().offset())),
        ex -> LOGGER.error(String.format("Unable to send message=[%s] due to : %s",
            customerJsonMasked, ex.getMessage())));

  }

}
