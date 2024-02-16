package com.bartoszmarkiewicz.inventory.exceptions;

import lombok.Getter;

@Getter
public class InventoryNotFoundException extends RuntimeException{

    private final Integer productId;

    public InventoryNotFoundException(Integer productId) {
        super(String.format("Product with ID %d not found", productId));
        this.productId = productId;
    }

}
