package com.bartoszmarkiewicz.inventory.exceptions;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException{

    private final Integer productId;

    public ProductNotFoundException(Integer productId) {
        super(String.format("Product with ID %d not found", productId));
        this.productId = productId;
    }

}
