package com.prokarma.engineering.customer.consumer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prokarma.engineering.customer.consumer.domain.Customer;
import com.prokarma.engineering.customer.consumer.repository.CustomerRepository;
import com.prokarma.engineering.customer.consumer.service.CustomerService;


@Service
public class CustomerServiceImpl implements CustomerService {
  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public void save(String customerJson) throws JsonProcessingException {
    Customer customer = new ObjectMapper().readValue(customerJson, Customer.class);
    customerRepository.saveAndFlush(customer);

  }

}
