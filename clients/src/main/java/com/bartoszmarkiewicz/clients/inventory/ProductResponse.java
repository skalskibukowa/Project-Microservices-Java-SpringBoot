package com.bartoszmarkiewicz.clients.inventory;

public record ProductResponse(
        Integer productId,
        Float productQuantity
) {
}