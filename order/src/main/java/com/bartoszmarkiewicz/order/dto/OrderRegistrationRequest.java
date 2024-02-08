package com.bartoszmarkiewicz.order.dto;

import com.bartoszmarkiewicz.order.model.OrderStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
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

}
