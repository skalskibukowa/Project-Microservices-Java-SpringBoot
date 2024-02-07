package com.bartoszmarkiewicz.fraud.controller;

import com.bartoszmarkiewicz.clients.fraud.FraudCheckResponse;
import com.bartoszmarkiewicz.fraud.service.FraudService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/fraud-check")
@RestController
@AllArgsConstructor
@Slf4j
public class FraudController {

    private final FraudService fraudService;

    @GetMapping(path = "{orderId}")
    public FraudCheckResponse isFraudulentOrder(@PathVariable("orderId") Integer orderId) {
        boolean isFraudulentOrder = fraudService.isFraudulentOrder(orderId);
        log.info("fraud check request for order {}", orderId);

        return new FraudCheckResponse(isFraudulentOrder);
    }


}
