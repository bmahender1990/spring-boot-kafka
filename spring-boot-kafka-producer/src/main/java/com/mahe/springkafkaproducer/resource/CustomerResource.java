package com.mahe.springkafkaproducer.resource;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mahe.springkafkaproducer.model.Customer;

@RestController
@RequestMapping("kafka")
public class CustomerResource {
  private static final Logger LOG = LogManager.getLogger(CustomerResource.class);
  @Autowired
  KafkaTemplate<String, Customer> kafkaTemplate;

  @Value("${app.topic}")
  private String topic;

  @PostMapping(path = "/customer", consumes = "application/json", produces = "application/json")
  public String post(@Valid @RequestBody Customer customer) {

    LOG.info("sending data to topic", customer);

    kafkaTemplate.send(topic, customer);

    LOG.info("sent data to topic", customer);
    return "{status:successfully published}";
  }

}
