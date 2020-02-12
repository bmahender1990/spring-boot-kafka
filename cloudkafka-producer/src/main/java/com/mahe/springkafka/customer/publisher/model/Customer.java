package com.mahe.springkafka.customer.publisher.model;

import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;

/**
 * Customer
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen",
    date = "2020-02-10T06:28:00.393Z")

public class Customer {
  @JsonProperty("customerNumber")
  private String customerNumber = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("birthdate")
  private String birthdate = null;

  @JsonProperty("country")
  private String country = null;

  @JsonProperty("countryCode")
  private String countryCode = null;

  @JsonProperty("mobileNumber")
  private String mobileNumber = null;

  @JsonProperty("email")
  private String email = null;

  /**
   * Gets or Sets customerStatus
   */
  public enum CustomerStatusEnum {
    OPEN("Open"),

    CLOSE("Close"),

    SUSPENDED("Suspended"),

    RESTORED("Restored");

    private String value;

    CustomerStatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CustomerStatusEnum fromValue(String text) {
      for (CustomerStatusEnum b : CustomerStatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("customerStatus")
  private CustomerStatusEnum customerStatus = null;

  @JsonProperty("address")
  private CustomerAddress address = null;

  public Customer customerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
    return this;
  }

  /**
   * Get customerNumber
   * 
   * @return customerNumber
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @NotBlank(message = "customerNumber is mandatory")
  @Size(max = 10, message = "customerNumber max length should be 10 only")
  @Pattern(regexp = "^[A-Za-z0-9]+$", message = "CustomerNumber should be alphanumeric")
  public String getCustomerNumber() {
    return customerNumber;
  }

  public void setCustomerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
  }

  public Customer firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * 
   * @return firstName
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @NotBlank(message = "firstName is mandatory")
  @Size(min = 10, max = 50, message = "firstName should have 10 to 50 characters")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Customer lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * 
   * @return lastName
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @NotBlank(message = "lastName is mandatory")
  @Size(min = 10, max = 50, message = "lastName should have 10 to 50 characters")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Customer birthdate(String birthdate) {
    this.birthdate = birthdate;
    return this;
  }

  /**
   * Get birthdate
   * 
   * @return birthdate
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull(message = "birthdate is mandatory")
  @Valid
  @JsonProperty("birthdate")
  @JsonFormat(pattern = "DD-MM-YYYY")
  public String getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }

  public Customer country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   * 
   * @return country
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @NotBlank(message = "country is mandatory")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Customer countryCode(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  /**
   * Get countryCode
   * 
   * @return countryCode
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @NotBlank(message = "countryCode is mandatory")
  @Size(max = 2, message = "countryCode should have maximum 2 characters")
  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public Customer mobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  /**
   * Get mobileNumber
   * 
   * @return mobileNumber
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull(message = "mobileNumber is mandatory")
  @Size(max = 10, message = "mobileNumber should have maximum 10 characters")
  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public Customer email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * 
   * @return email
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Email
  @NotBlank(message = "email is mandatory")
  @Size(max = 50, message = "email should have maximum 50 characters")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Customer customerStatus(CustomerStatusEnum customerStatus) {
    this.customerStatus = customerStatus;
    return this;
  }

  /**
   * Get customerStatus
   * 
   * @return customerStatus
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull(message = "customerStatus is mandatory")

  public CustomerStatusEnum getCustomerStatus() {
    return customerStatus;
  }

  public void setCustomerStatus(CustomerStatusEnum customerStatus) {
    this.customerStatus = customerStatus;
  }

  public Customer address(CustomerAddress address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * 
   * @return address
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public CustomerAddress getAddress() {
    return address;
  }

  public void setAddress(CustomerAddress address) {
    this.address = address;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer customer = (Customer) o;
    return Objects.equals(this.customerNumber, customer.customerNumber)
        && Objects.equals(this.firstName, customer.firstName)
        && Objects.equals(this.lastName, customer.lastName)
        && Objects.equals(this.birthdate, customer.birthdate)
        && Objects.equals(this.country, customer.country)
        && Objects.equals(this.countryCode, customer.countryCode)
        && Objects.equals(this.mobileNumber, customer.mobileNumber)
        && Objects.equals(this.email, customer.email)
        && Objects.equals(this.customerStatus, customer.customerStatus)
        && Objects.equals(this.address, customer.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerNumber, firstName, lastName, birthdate, country, countryCode,
        mobileNumber, email, customerStatus, address);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Customer {\n");

    sb.append("    customerNumber: ").append(toIndentedString(customerNumber)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    birthdate: ").append(toIndentedString(birthdate)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
    sb.append("    mobileNumber: ").append(toIndentedString(mobileNumber)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    customerStatus: ").append(toIndentedString(customerStatus)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

