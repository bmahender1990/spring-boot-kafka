package com.mahe.springkafka.customer.publisher.resource;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahe.springkafka.customer.publisher.api.KafkaApi;
import com.mahe.springkafka.customer.publisher.model.Customer;
import com.mahe.springkafka.customer.publisher.model.CustomerResponse;
import com.mahe.springkafka.customer.publisher.service.impl.KafkaProducerServiceImpl;
import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen",
    date = "2020-02-10T06:28:00.393Z")

@Controller
public class KafkaApiController implements KafkaApi {

  private static final Logger LOGGER = LogManager.getLogger(KafkaApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;
  @Autowired
  private KafkaProducerServiceImpl producer;

  @Autowired
  public KafkaApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<?> customer(
      @ApiParam(value = "customer object that needs to be post to kafka",
          required = true) @Valid @RequestBody Customer customer,
      @ApiParam(value = "Authorization token", required = true) @RequestHeader(
          value = "Authorization", required = true) String authorization,
      @ApiParam(value = "") @RequestHeader(value = "Application-Id",
          required = false) String applicationId,
      @ApiParam(value = "") @RequestHeader(value = "Activity-Id",
          required = false) String activityId) {


    producer.send(customer);

    CustomerResponse response = new CustomerResponse("success", "successfully pussed");
    return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
  }

}
