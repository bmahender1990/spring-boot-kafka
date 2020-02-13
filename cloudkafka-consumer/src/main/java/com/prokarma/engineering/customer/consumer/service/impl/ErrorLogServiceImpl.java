package com.prokarma.engineering.customer.consumer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prokarma.engineering.customer.consumer.domain.ErrorLog;
import com.prokarma.engineering.customer.consumer.repository.ErrorLogRepository;
import com.prokarma.engineering.customer.consumer.service.ErrorLogService;

@Service
public class ErrorLogServiceImpl implements ErrorLogService {

  @Autowired
  private ErrorLogRepository errorLogRepository;

  @Override
  public void save(Exception exception, String payload) {
    ErrorLog customerErrorLog = new ErrorLog();
    customerErrorLog.setErrorDescription(exception.getMessage());
    customerErrorLog.setErrorType("Exception");
    customerErrorLog.setPayload(payload);

    errorLogRepository.saveAndFlush(customerErrorLog);
  }

}
