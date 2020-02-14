package com.prokarma.engineering.customer.publisher.resource;

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
import com.prokarma.engineering.customer.publisher.api.AuthenticateApi;
import com.prokarma.engineering.customer.publisher.model.User;
import com.prokarma.engineering.customer.publisher.security.model.AuthenticationResponse;
import com.prokarma.engineering.customer.publisher.security.util.JwtUtil;
import com.prokarma.engineering.customer.publisher.service.impl.AppUserDetailsServiceImpl;
import io.swagger.annotations.ApiParam;


@Controller
public class AuthenticateApiController implements AuthenticateApi {

  private static final Logger LOGGER = LogManager.getLogger(AuthenticateApiController.class);

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtTokenUtil;

  @Autowired
  private AppUserDetailsServiceImpl userDetailsService;


  public ResponseEntity<AuthenticationResponse> authenticate(
      @ApiParam(value = "Created user object", required = true) @Valid @RequestBody User user) {

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

    final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

    final String jwt = jwtTokenUtil.generateToken(userDetails);

    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }

}
