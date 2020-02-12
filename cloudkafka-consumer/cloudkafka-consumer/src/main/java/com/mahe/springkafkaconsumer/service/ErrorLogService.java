package com.mahe.springkafkaconsumer.service;

public interface ErrorLogService {

  void save(Exception exception, String payload);
}
