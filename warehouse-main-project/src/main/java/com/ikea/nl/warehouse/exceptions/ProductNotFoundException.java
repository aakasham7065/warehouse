package com.ikea.nl.warehouse.exceptions;

/**
 * When a product is not found
 */
public class ProductNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String msg) {
        super(msg);
    }

}
