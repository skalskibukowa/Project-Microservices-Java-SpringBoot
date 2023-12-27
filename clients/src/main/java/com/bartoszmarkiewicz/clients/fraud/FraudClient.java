package com.bartoszmarkiewicz.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("fraud")
public interface FraudClient {
    @GetMapping(path = "api/v1/fraud-check/{orderId}")
    FraudCheckResponse isFraudulentOrder(@PathVariable("orderId") Long orderId);
}
