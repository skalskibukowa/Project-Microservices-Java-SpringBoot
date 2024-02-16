package com.bartoszmarkiewicz.inventory.dto;

import java.time.LocalDateTime;

public record InventoryRecord(
        Integer productId,
        String productName,
        Integer productQuantity,
        Float productPrice,
        LocalDateTime createdAt) {


    public Integer getProductQuantity() {
        return productQuantity;
    }

    public Float getProductPrice() {return productPrice;}

    public String getProductName() {return productName;}

    public LocalDateTime createdAt(LocalDateTime now) {
        return now;
    }
}

