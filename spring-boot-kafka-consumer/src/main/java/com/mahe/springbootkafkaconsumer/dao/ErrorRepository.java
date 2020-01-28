package com.mahe.springbootkafkaconsumer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahe.springbootkafkaconsumer.model.AuditLog;
import com.mahe.springbootkafkaconsumer.model.ErrorLog;


@Repository
public interface ErrorRepository extends JpaRepository<ErrorLog, Integer> {


}
