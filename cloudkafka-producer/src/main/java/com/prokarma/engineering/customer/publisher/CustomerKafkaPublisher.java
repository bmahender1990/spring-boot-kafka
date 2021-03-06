package com.prokarma.engineering.customer.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class CustomerKafkaPublisher {

  public static void main(String[] args) {
    SpringApplication.run(CustomerKafkaPublisher.class, args);
  }


  /*
   * @Bean public CommandLineRunner setupDefaultUser(UserServiceImpl service) { return args -> {
   * service.save(new User("user", // username "user", // password Arrays.asList(new
   * UserRole("USER"), new UserRole("ACTUATOR")), // roles true// Active )); }; }
   */

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }



}
