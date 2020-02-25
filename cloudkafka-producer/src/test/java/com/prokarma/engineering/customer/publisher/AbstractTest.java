package com.prokarma.engineering.customer.publisher;

import java.io.IOException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerKafkaPublisherTests.class)
@WebAppConfiguration
public abstract class AbstractTest {
  protected MockMvc mvc;
  @Autowired
  WebApplicationContext webApplicationContext;

  @Autowired
  private FilterChainProxy springSecurityFilterChain;

  JacksonJsonParser jsonParser = new JacksonJsonParser();

  protected void setUp() {
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .addFilter(springSecurityFilterChain).build();
  }

  protected String mapToJson(Object obj) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsString(obj);
  }

  protected <T> T mapFromJson(String json, Class<T> clazz)
      throws JsonParseException, JsonMappingException, IOException {

    ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.readValue(json, clazz);
  }

  public JacksonJsonParser JacksonJsonParser() {
    return jsonParser;
  }


}
