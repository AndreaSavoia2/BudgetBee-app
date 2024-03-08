package com.sn.budgetbee.exception;

public class EntranceNotFoundException extends RuntimeException{

    public EntranceNotFoundException(String message) {
        super(message);
    }

    public EntranceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntranceNotFoundException(Throwable cause) {
        super(cause);
    }
}
