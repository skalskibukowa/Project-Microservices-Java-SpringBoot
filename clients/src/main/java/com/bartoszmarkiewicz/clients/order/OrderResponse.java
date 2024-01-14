package com.bartoszmarkiewicz.clients.order;

public record OrderResponse(
        Integer productId,
        Integer orderAmount
) {
}
