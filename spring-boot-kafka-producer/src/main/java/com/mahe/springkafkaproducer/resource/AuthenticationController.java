package com.mahe.springkafkaproducer.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mahe.springkafkaproducer.security.model.AuthenticationRequest;
import com.mahe.springkafkaproducer.security.model.AuthenticationResponse;
import com.mahe.springkafkaproducer.security.util.JwtUtil;
import com.mahe.springkafkaproducer.service.AppUserDetailsService;

@RestController
class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtTokenUtil;

  @Autowired
  private AppUserDetailsService userDetailsService;

  @PostMapping(value = "/authenticate")
  public ResponseEntity<?> createAuthenticationToken(
      @RequestBody AuthenticationRequest authenticationRequest) throws Exception {

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          authenticationRequest.getUsername(), authenticationRequest.getPassword()));
    } catch (BadCredentialsException e) {
      throw new Exception("Incorrect username or password", e);
    }


    final UserDetails userDetails =
        userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

    final String jwt = jwtTokenUtil.generateToken(userDetails);

    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }

}
