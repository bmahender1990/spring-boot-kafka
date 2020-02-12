package com.mahe.springkafkaconsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mahe.springkafkaconsumer.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

}
