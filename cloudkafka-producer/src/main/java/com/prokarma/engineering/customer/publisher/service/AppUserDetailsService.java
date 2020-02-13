package com.prokarma.engineering.customer.publisher.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserDetailsService extends UserDetailsService {

  public UserDetails loadUserByUsername(String s);
}
