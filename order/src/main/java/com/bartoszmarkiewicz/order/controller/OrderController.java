package com.bartoszmarkiewicz.order.controller;

import com.bartoszmarkiewicz.order.dto.OrderRegistrationRequest;
import com.bartoszmarkiewicz.order.service.OrderService;
import com.bartoszmarkiewicz.order.model.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> registerOrder(@RequestBody OrderRegistrationRequest orderRegistrationRequest) { // Order
        log.info("new order registration {}", orderRegistrationRequest);
        Order order = orderService.registerOrder(orderRegistrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
