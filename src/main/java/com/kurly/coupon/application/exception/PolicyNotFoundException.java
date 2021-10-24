package com.kurly.coupon.application.exception;

public class PolicyNotFoundException extends RuntimeException {
    public PolicyNotFoundException() {
        super();
    }

    public PolicyNotFoundException(String message) {
        super(message);
    }

    public PolicyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PolicyNotFoundException(Throwable cause) {
        super(cause);
    }
}
