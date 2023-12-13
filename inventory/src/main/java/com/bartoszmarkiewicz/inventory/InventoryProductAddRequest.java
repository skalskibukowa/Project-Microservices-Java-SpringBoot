package com.bartoszmarkiewicz.inventory;

import java.time.LocalDateTime;

public record InventoryProductAddRequest(
        Long productId,
        String productName,
        Long productQuantity,
        Float productPrice,
        LocalDateTime productDateStatus) {

}

