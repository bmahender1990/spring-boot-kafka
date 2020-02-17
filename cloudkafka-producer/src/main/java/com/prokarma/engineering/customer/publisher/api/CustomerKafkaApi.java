package com.prokarma.engineering.customer.publisher.api;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import com.prokarma.engineering.customer.publisher.model.Customer;
import io.swagger.annotations.ApiParam;

@RequestMapping(value = "")
public interface CustomerKafkaApi {


  @PostMapping(value = "/prokarma/v1/customer", produces = {"application/json"},
      consumes = {"application/json"})
  default ResponseEntity<?> customer(@ApiParam(required = true) @Valid @RequestBody Customer body,
      @ApiParam(value = "Authorization token", required = true) @RequestHeader(
          value = "Authorization", required = true) String authorization,
      @ApiParam(value = "") @RequestHeader(value = "Application-Id",
          required = false) String applicationId,
      @ApiParam(value = "") @RequestHeader(value = "Activity-Id",
          required = false) String activityId) {


    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }

}
