package com.prokarma.engineering.customer.publisher.exception;

public class KafkaNetworkException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public KafkaNetworkException(String message) {
    super(message);
  }
}
