package com.prokarma.engineering.customer.publisher.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.prokarma.engineering.customer.publisher.model.User;
import com.prokarma.engineering.customer.publisher.repositories.UserRepository;
import com.prokarma.engineering.customer.publisher.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository repo;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public void save(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    repo.save(user);
  }

}
