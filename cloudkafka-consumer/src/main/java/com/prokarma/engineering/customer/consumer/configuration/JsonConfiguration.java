package com.prokarma.engineering.customer.consumer.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prokarma.engineering.customer.consumer.domain.Customer;

@Configuration
public class JsonConfiguration {

  private static final Logger LOG = LogManager.getLogger(JsonConfiguration.class);
  @Autowired
  public ObjectMapper objectMapper;


  public Customer jsonStringToObjectConverter(String customerJson) {
    Customer customer = new Customer();
    try {
      customer =
          new ObjectMapper().enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
              .readValue(customerJson, Customer.class);
    } catch (JsonProcessingException e) {
      LOG.error(e.getMessage());
    }
    return customer;
  }

}
