package com.mahe.springkafkaconsumer.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.mahe.springkafkaconsumer.service.AuditLogService;
import com.mahe.springkafkaconsumer.service.CustomerService;
import com.mahe.springkafkaconsumer.service.ErrorLogService;


@Service
public class KafkaConsumer {
  private static final Logger LOGGER = LogManager.getLogger(KafkaConsumer.class);

  @Autowired
  private CustomerService customerService;

  @Autowired
  private AuditLogService auditLogService;

  @Autowired
  private ErrorLogService errorLogService;

  @KafkaListener(topics = "${cloudkarafka.topic}")
  public void onCustomerTopic(String payload) {
    try {
      LOGGER.info("Message Received -- > {}", payload);

      customerService.save(payload);
      auditLogService.save(payload);

    } catch (Exception exception) {
      errorLogService.save(exception, payload);
    }

  }

}
