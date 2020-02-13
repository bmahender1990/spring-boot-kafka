package com.prokarma.engineering.customer.publisher.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.prokarma.engineering.customer.publisher.model.CustomerResponse;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex,
      WebRequest request) {
    CustomerResponse errorDetails =
        new CustomerResponse("error", ex.getMessage(), "ResourceNotFoundException");
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
    CustomerResponse errorDetails =
        new CustomerResponse("error", ex.getMessage(), "GeneralException");
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    String errormsg = "";
    if (ex.getBindingResult() != null && ex.getBindingResult().getAllErrors() != null
        && !ex.getBindingResult().getAllErrors().isEmpty()) {
      errormsg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    }
    CustomerResponse errorDetails =
        new CustomerResponse("error", errormsg, "InvalidRequestException");
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }
}
