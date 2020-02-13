package com.prokarma.engineering.customer.consumer.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "ERROR_LOG")
public class ErrorLog implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "ERROR_TYPE")
  private String errorType;

  @Lob
  @Column(name = "ERROR_DESCRIPTION", length = 3000)
  private String errorDescription;

  @Lob
  @Column(name = "PAYLOAD", length = 3000)
  private String payload;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getErrorType() {
    return errorType;
  }

  public void setErrorType(String errorType) {
    this.errorType = errorType;
  }

  public String getErrorDescription() {
    return errorDescription;
  }

  public void setErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
  }

  public String getPayload() {
    return payload;
  }

  public void setPayload(String payload) {
    this.payload = payload;
  }

}
