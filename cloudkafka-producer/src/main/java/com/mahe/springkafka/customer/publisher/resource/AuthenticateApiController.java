package com.mahe.springkafka.customer.publisher.resource;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahe.springkafka.customer.publisher.api.AuthenticateApi;
import com.mahe.springkafka.customer.publisher.model.User;
import com.mahe.springkafka.customer.publisher.security.model.AuthenticationResponse;
import com.mahe.springkafka.customer.publisher.security.util.JwtUtil;
import com.mahe.springkafka.customer.publisher.service.AppUserDetailsService;
import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen",
    date = "2020-02-10T06:28:00.393Z")

@Controller
public class AuthenticateApiController implements AuthenticateApi {

  private static final Logger LOGGER = LogManager.getLogger(AuthenticateApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtTokenUtil;

  @Autowired
  private AppUserDetailsService userDetailsService;



  @org.springframework.beans.factory.annotation.Autowired
  public AuthenticateApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  public ResponseEntity<AuthenticationResponse> authenticate(
      @ApiParam(value = "Created user object", required = true) @Valid @RequestBody User user) {

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

    final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

    final String jwt = jwtTokenUtil.generateToken(userDetails);

    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }

}
