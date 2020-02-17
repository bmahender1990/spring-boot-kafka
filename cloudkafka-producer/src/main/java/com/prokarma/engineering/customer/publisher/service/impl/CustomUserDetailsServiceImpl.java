
package com.prokarma.engineering.customer.publisher.service.impl;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.prokarma.engineering.customer.publisher.repositories.UserRepository;


@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository repo;

  @Override
  public UserDetails loadUserByUsername(String username) {
    return repo.findByUsername(username)
        .map(u -> new User(u.getUsername(), u.getPassword(), u.isActive(), u.isActive(),
            u.isActive(), u.isActive(),
            AuthorityUtils.createAuthorityList(
                u.getRoles().stream().map(r -> "ROLE_" + r.getName().toUpperCase())
                    .collect(Collectors.toList()).toArray(new String[] {}))))
        .orElseThrow(() -> new UsernameNotFoundException(
            "No user with " + "the name " + username + "was found in the database"));
  }

}
