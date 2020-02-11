package com.mahe.springkafkaproducer.validation;

public class InvalidUserNameOrPasswordException extends Exception {

  private static final long serialVersionUID = 1L;

  public InvalidUserNameOrPasswordException(String message) {
    super(message);
  }
}

