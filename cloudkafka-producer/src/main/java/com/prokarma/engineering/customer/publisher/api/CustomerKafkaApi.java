package com.prokarma.engineering.customer.publisher.api;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.prokarma.engineering.customer.publisher.model.Customer;
import com.prokarma.engineering.customer.publisher.model.CustomerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@Api(value = "kafka", description = "the kafka API")
@RequestMapping(value = "")
public interface CustomerKafkaApi {

  @ApiOperation(value = "Post customer data to kafka", nickname = "customer",
      notes = "Returns status", authorizations = {@Authorization(value = "o_auth", scopes = {})},
      tags = {"kafka/customer",})
  @ApiResponses(value = {
      @ApiResponse(code = 405, message = "Invalid input", response = CustomerResponse.class)})
  @RequestMapping(value = "/kafka/customer", produces = {"application/json"},
      consumes = {"application/json"}, method = RequestMethod.POST)
  default ResponseEntity<?> customer(
      @ApiParam(value = "customer object that needs to be post to kafka",
          required = true) @Valid @RequestBody Customer body,
      @ApiParam(value = "Authorization token", required = true) @RequestHeader(
          value = "Authorization", required = true) String authorization,
      @ApiParam(value = "") @RequestHeader(value = "Application-Id",
          required = false) String applicationId,
      @ApiParam(value = "") @RequestHeader(value = "Activity-Id",
          required = false) String activityId) {


    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

  }

}
