package com.prokarma.engineering.customer.publisher.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JsonConfiguration {

  private static final Logger LOG = LogManager.getLogger(JsonConfiguration.class);
  @Autowired
  public ObjectMapper objectMapper;


  public String jsonToStringConverter(Object customer) {
    String customerString = "";
    try {
      objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
      customerString = objectMapper.writeValueAsString(customer);
    } catch (JsonProcessingException e) {
      LOG.error(e.getMessage());
    }
    return customerString;
  }

}
