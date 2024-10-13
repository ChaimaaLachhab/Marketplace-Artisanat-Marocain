package com.artisanat_backend.exception;

public class UnsupportedEntityException extends IllegalArgumentException {
    public UnsupportedEntityException(String message) {
        super(message);
    }
}