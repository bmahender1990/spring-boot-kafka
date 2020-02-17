package com.prokarma.engineering.customer.consumer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prokarma.engineering.customer.consumer.configuration.JsonConfiguration;
import com.prokarma.engineering.customer.consumer.domain.Customer;
import com.prokarma.engineering.customer.consumer.repository.CustomerRepository;
import com.prokarma.engineering.customer.consumer.service.CustomerService;



@Service
public class CustomerServiceImpl implements CustomerService {
  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private JsonConfiguration jsonConfiguration;

  @Override
  public void save(String customerJson) {
    Customer customer = jsonConfiguration.jsonStringToObjectConverter(customerJson);
    customerRepository.saveAndFlush(customer);
  }

}
