package com.prokarma.engineering.customer.publisher.resource;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import com.prokarma.engineering.customer.publisher.api.CustomerKafkaApi;
import com.prokarma.engineering.customer.publisher.model.Customer;
import com.prokarma.engineering.customer.publisher.model.CustomerResponse;
import com.prokarma.engineering.customer.publisher.service.impl.KafkaProducerServiceImpl;
import io.swagger.annotations.ApiParam;


@Controller
public class CustomerKafkaApiController implements CustomerKafkaApi {

  @Autowired
  private KafkaProducerServiceImpl producer;

  @Override
  public ResponseEntity<?> customer(
      @ApiParam(required = true) @Valid @RequestBody Customer customer,
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
