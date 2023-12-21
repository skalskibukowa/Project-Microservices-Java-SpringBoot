package com.bartoszmarkiewicz.fraud;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudService {

    @Autowired
    private final FraudRepository fraudRepository;

    public boolean isFraudulentOrder(Integer orderId) {
        fraudRepository.save(
                Fraud.builder()
                        .orderId(orderId)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
