package com.prokarma.engineering.customer.publisher.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.prokarma.engineering.customer.publisher.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
}
