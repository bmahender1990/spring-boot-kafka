package com.mahe.springkafkaconsumer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mahe.springkafkaconsumer.domain.ErrorLog;
import com.mahe.springkafkaconsumer.repository.ErrorLogRepository;
import com.mahe.springkafkaconsumer.service.ErrorLogService;

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
