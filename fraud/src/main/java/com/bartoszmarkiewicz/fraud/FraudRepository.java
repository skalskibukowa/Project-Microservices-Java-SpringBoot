package com.bartoszmarkiewicz.fraud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudRepository extends JpaRepository<Fraud, Integer> {
}
