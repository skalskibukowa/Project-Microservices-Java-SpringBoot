package com.bartoszmarkiewicz.inventory.exceptions;

public class ProductValidationException extends RuntimeException{

    private final String message;

    public ProductValidationException(String message) {
        super(message);
        this.message = message;
    }

    public String getJsonErrorResponse() {
        return "{ \"message\": \"" + message + "\" }";
    }

}
