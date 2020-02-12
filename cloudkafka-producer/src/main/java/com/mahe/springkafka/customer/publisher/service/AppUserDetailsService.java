package com.mahe.springkafka.customer.publisher.service;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String s) {
    return new User("mahe", "mahe", new ArrayList<>());
  }
}