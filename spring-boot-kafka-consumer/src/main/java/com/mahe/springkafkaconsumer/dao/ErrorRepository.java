package com.mahe.springkafkaconsumer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahe.springkafkaconsumer.model.AuditLog;
import com.mahe.springkafkaconsumer.model.ErrorLog;


@Repository
public interface ErrorRepository extends JpaRepository<ErrorLog, Integer> {


}
