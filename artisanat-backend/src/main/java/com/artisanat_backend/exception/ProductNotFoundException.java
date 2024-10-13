package com.artisanat_backend.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long prductId) {
        super("Product with ID " + prductId + " not found.");
    }
}
