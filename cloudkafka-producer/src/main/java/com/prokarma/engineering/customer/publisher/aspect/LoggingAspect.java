package com.prokarma.engineering.customer.publisher.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.prokarma.engineering.customer.publisher.configuration.JsonConfiguration;

@Component
@Aspect
public class LoggingAspect {
  private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

  @Pointcut("within(@org.springframework.stereotype.Controller *)")
  public void controller() {}

  @Pointcut("execution(@(@org.springframework.web.bind.annotation.RequestMapping *) * *(..))")
  private void requestMappingAnnotations() {}

  @Pointcut("execution(* *.*(..))")
  protected void allMethod() {}

  @Autowired
  private JsonConfiguration jsonConfiguration;

  @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) "
      + "|| @annotation(org.springframework.web.bind.annotation.GetMapping)"
      + "|| @annotation(org.springframework.web.bind.annotation.PostMapping)"
      + "|| @annotation(org.springframework.web.bind.annotation.PathVariable)"
      + "|| @annotation(org.springframework.web.bind.annotation.PutMapping)"
      + "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
  public void mappingAnnotations() {}

  @Before("controller() && allMethod() && args(customer,..)")
  public void logBefore(JoinPoint joinPoint, Object customer) {
    LOGGER.info("Request : {}", jsonConfiguration.jsonToStringConverter(customer));
  }

  @AfterReturning(pointcut = "(controller() || requestMappingAnnotations()) && allMethod()",
      returning = "result")
  public void logAfter(JoinPoint joinPoint, Object result) {
    LOGGER.info("Returning : {}", jsonConfiguration.jsonToStringConverter(result));
  }


}
