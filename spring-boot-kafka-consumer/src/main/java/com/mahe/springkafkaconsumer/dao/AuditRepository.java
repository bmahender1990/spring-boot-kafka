package com.mahe.springkafkaconsumer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahe.springkafkaconsumer.model.AuditLog;


@Repository
public interface AuditRepository extends JpaRepository<AuditLog, Integer> {


}
