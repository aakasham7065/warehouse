package com.ikea.nl.warehouse.exceptions;

public class InventoryLoaderException extends RuntimeException {
    public InventoryLoaderException(String message) {
        super(message);
    }

    public InventoryLoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
