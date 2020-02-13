package com.prokarma.engineering.customer.publisher.exception;

public class InvalidUserNameOrPasswordException extends Exception {

  private static final long serialVersionUID = 1L;

  public InvalidUserNameOrPasswordException(String message) {
    super(message);
  }
}

