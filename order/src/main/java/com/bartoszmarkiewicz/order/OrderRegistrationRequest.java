package com.bartoszmarkiewicz.order;

import java.time.LocalDateTime;

public record OrderRegistrationRequest(
        Long customerId,

        Long productId,

        Long orderId,

        String productName,
        Double orderValue,

        Integer orderAmount,
        LocalDateTime orderCreatedAt,
        String shippingAddress,
        String phoneNumber,
        OrderStatus orderStatus) {
    public LocalDateTime orderCreatedAt(LocalDateTime now) {
        return now;
    }
}
