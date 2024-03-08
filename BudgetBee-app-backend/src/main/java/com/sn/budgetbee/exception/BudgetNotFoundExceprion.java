package com.sn.budgetbee.exception;

public class BudgetNotFoundExceprion extends RuntimeException {
    public BudgetNotFoundExceprion(String message) {
        super(message);
    }

    public BudgetNotFoundExceprion(String message, Throwable cause) {
        super(message, cause);
    }

    public BudgetNotFoundExceprion(Throwable cause) {
        super(cause);
    }
}
