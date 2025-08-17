package com.tushar.exception;

public class InvoiceServiceException extends RuntimeException {
    public InvoiceServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvoiceServiceException(String message) {
        super(message);
    }
}
