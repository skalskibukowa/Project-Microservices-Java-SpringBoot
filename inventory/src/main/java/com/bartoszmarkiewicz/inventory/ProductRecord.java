package com.bartoszmarkiewicz.inventory;

import java.time.LocalDateTime;


public record ProductRecord(
        Integer productId,
        String productName,
        Float productQuantity,
        Float productPrice,
        LocalDateTime createdAt) {


    public Float getProductQuantity() {
        return productQuantity;
    }

    public Float getProductPrice() {return productPrice;}

    public String getProductName() {return productName;}

    public LocalDateTime createdAt(LocalDateTime now) {
        return now;
    }
}

