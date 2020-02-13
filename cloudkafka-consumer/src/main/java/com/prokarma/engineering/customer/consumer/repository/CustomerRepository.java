package com.prokarma.engineering.customer.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.prokarma.engineering.customer.consumer.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

}
