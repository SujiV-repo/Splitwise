package com.project.splitwise.exception;

public class ExpenseNotFoundException extends Exception{
    public ExpenseNotFoundException() {
    }

    public ExpenseNotFoundException(String message) {
        super(message);
    }

    public ExpenseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
