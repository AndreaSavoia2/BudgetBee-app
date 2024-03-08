package com.sn.budgetbee.exception;

public class ExitNotFoundException extends RuntimeException{

    public ExitNotFoundException(String message) {
        super(message);
    }

    public ExitNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExitNotFoundException(Throwable cause) {
        super(cause);
    }
}
