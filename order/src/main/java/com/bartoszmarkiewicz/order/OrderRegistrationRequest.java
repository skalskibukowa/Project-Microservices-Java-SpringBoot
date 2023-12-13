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
}
