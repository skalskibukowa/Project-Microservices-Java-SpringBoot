package com.bartoszmarkiewicz.order;

import java.time.LocalDateTime;

public record OrderRegistrationRequest(
        Integer orderId,
        Integer customerId,

        Integer productId,

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
