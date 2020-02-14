package com.prokarma.engineering.customer.publisher.common;

import org.springframework.stereotype.Component;
import com.prokarma.engineering.customer.publisher.model.Customer;
import com.prokarma.engineering.customer.publisher.model.CustomerAddress;


@Component
public class LogMaskingConverter {

  public Customer maskCustomer(Customer customer) {
    Customer customerMasked = new Customer();
    customerMasked.setCustomerNumber(
        customer.getCustomerNumber().substring(0, customer.getCustomerNumber().length() - 4)
            + "****");
    customerMasked.setFirstName(customer.getFirstName());
    customerMasked.setLastName(customer.getLastName());
    customerMasked.setBirthdate(customer.getBirthdate());
    customerMasked.setCountry(customer.getCountry());
    customerMasked.setCountryCode(customer.getCountryCode());
    customerMasked.setMobileNumber("****" + customer.getMobileNumber().substring(4));
    customerMasked.setEmail("****" + customer.getEmail().substring(4));
    customerMasked.setCustomerStatus(customer.getCustomerStatus());
    CustomerAddress address = new CustomerAddress();
    address.setAddressLine1(customer.getAddress().getAddressLine1());
    address.setAddressLine2(customer.getAddress().getAddressLine2());
    address.setStreet(customer.getAddress().getStreet());
    address.setPostalCode(customer.getAddress().getPostalCode());
    customerMasked.setAddress(address);
    return customerMasked;
  }


}
