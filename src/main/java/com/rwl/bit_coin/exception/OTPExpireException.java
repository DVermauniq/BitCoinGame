package com.rwl.bit_coin.exception;

public class OTPExpireException extends RuntimeException {
    public OTPExpireException(String message) {
        super(message);
    }
}

