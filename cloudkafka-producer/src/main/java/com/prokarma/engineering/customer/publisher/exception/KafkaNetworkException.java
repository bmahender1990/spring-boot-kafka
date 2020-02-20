package com.prokarma.engineering.customer.publisher.exception;

public class KafkaNetworkException extends Exception {

  private static final long serialVersionUID = 1L;

  public KafkaNetworkException(String message) {
    super(message);
  }
}
