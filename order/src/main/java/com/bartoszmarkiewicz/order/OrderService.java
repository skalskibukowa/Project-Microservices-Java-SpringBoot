package com.bartoszmarkiewicz.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void registerOrder(OrderRegistrationRequest request) {

        // Create order
        Order order = Order.builder()
                .customerId(request.customerId())
                .productName(request.productName())
                .orderAmount(request.orderAmount())
                .orderValue(request.orderValue())
                .phoneNumber(request.phoneNumber())
                .shippingAddress(request.shippingAddress())
                .orderStatus(request.orderStatus())
                .orderCreatedAt(request.orderCreatedAt())
                .build();

        // Register order in the DB
        orderRepository.saveAndFlush(order);

    }
}
