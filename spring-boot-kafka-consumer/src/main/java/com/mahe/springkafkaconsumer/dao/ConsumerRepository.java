package com.mahe.springkafkaconsumer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahe.springkafkaconsumer.model.Customer;


@Repository
public interface ConsumerRepository extends JpaRepository<Customer, Integer> {


}
