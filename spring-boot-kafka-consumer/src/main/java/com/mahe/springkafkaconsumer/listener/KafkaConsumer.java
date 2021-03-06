package com.mahe.springkafkaconsumer.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahe.springkafkaconsumer.model.AuditLog;
import com.mahe.springkafkaconsumer.model.Customer;
import com.mahe.springkafkaconsumer.model.ErrorLog;
import com.mahe.springkafkaconsumer.service.ConsumerService;


@Service
public class KafkaConsumer {
  private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);

  @Autowired(required = true)
  private ConsumerService service;

  @KafkaListener(topics = "KafkaDemo", groupId = "group_id")
  public void consume(ConsumerRecord message) {

    LOG.info("consume data" + message.value());
    String customerStr = (String) message.value();

    ObjectMapper objectMapper = new ObjectMapper();
    try {
      Customer customer = objectMapper.readValue(customerStr, Customer.class);

      service.addCustomer(customer);

      AuditLog audit = new AuditLog();
      audit.setCustomerNumber(customer.getCustomerNumber());
      audit.setPayload(customerStr);
      service.addAudit(audit);

    } catch (Exception e) {
      ErrorLog error = new ErrorLog(e.getLocalizedMessage(), e.getMessage(), customerStr);
      service.addError(error);
    }

  }

}
