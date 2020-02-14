package com.prokarma.engineering.customer.publisher.api;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.prokarma.engineering.customer.publisher.model.User;
import com.prokarma.engineering.customer.publisher.security.model.AuthenticationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(value = "authenticate", description = "the authenticate API")
@RequestMapping(value = "")
public interface AuthenticateApi {

  @ApiOperation(value = "authenticate user", nickname = "authenticate",
      notes = "This can only be done by the logged in user.", tags = {"authenticate",})
  @ApiResponses(value = {@ApiResponse(code = 200, message = "successful operation")})
  @RequestMapping(value = "/authenticate", produces = {"application/json"},
      consumes = {"application/json"}, method = RequestMethod.POST)
  default ResponseEntity<AuthenticationResponse> authenticate(
      @ApiParam(value = "Created user object", required = true) @Valid @RequestBody User body) {

    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}
