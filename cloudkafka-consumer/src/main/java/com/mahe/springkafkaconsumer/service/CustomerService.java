package com.mahe.springkafkaconsumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface CustomerService {

  void save(String customerJson) throws JsonProcessingException;

}
