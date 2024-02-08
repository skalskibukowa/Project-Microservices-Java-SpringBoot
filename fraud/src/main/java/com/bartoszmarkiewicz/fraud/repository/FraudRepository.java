package com.bartoszmarkiewicz.fraud.repository;

import com.bartoszmarkiewicz.fraud.model.Fraud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudRepository extends JpaRepository<Fraud, Integer> {
}
