package com.prokarma.engineering.customer.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class SampleKafkaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SampleKafkaApplication.class, args);
  }



}