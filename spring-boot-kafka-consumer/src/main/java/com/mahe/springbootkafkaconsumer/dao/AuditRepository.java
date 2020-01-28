package com.mahe.springbootkafkaconsumer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahe.springbootkafkaconsumer.model.AuditLog;


@Repository
public interface AuditRepository extends JpaRepository<AuditLog, Integer> {


}
