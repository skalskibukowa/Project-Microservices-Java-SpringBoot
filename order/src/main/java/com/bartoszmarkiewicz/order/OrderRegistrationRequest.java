package com.bartoszmarkiewicz.order;

import java.time.LocalDateTime;

public record OrderRegistrationRequest(
        Long customerId,
        String productName,
        Double orderValue,

        Integer orderAmount,
        LocalDateTime orderCreatedAt,
        String shippingAddress,
        String phoneNumber,
        OrderStatus orderStatus) {
}
