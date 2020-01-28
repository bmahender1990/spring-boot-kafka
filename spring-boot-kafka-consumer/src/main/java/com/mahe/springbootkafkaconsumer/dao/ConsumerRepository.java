package com.mahe.springbootkafkaconsumer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahe.springbootkafkaconsumer.model.Customer;


@Repository
public interface ConsumerRepository extends JpaRepository<Customer, Integer> {


}
