package com.bartoszmarkiewicz.inventory.exceptions;

public class InventoryValidationException extends RuntimeException{

    private final String message;

    public InventoryValidationException(String message) {
        super(message);
        this.message = message;
    }

    public String getJsonErrorResponse() {
        return "{ \"message\": \"" + message + "\" }";
    }

}
