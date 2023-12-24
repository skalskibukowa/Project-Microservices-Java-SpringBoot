package com.bartoszmarkiewicz.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final RestTemplate restTemplate;

    public void registerOrder(OrderRegistrationRequest request) {

        // Create order
        Order order = Order.builder()
                .orderId(request.orderId())
                .customerId(request.customerId())
                .productId(request.productId())
                .productName(request.productName())
                .orderAmount(request.orderAmount())
                .orderValue(request.orderValue())
                .phoneNumber(request.phoneNumber())
                .shippingAddress(request.shippingAddress())
                .orderStatus(request.orderStatus())
                .orderCreatedAt(request.orderCreatedAt(LocalDateTime.now()))
                .build();

        // Register order in the DB
        orderRepository.saveAndFlush(order);
        // check if fraudster
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{orderId}", //  Declared microservice FRAUD
                    FraudCheckResponse.class,
                    order.getOrderId()
                );

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

    }
}
