package com.prokarma.engineering.customer.publisher.exception;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen",
    date = "2020-02-10T06:28:00.393Z")

public class ApiException extends Exception {
  private static final long serialVersionUID = 1L;
  private int code;

  public ApiException(int code, String msg) {
    super(msg);
    this.code = code;
  }
}
