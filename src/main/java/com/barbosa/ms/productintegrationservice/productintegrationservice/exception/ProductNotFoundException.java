package com.barbosa.ms.productintegrationservice.productintegrationservice.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
