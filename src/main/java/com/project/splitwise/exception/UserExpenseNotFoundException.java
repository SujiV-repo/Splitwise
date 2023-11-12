package com.project.splitwise.exception;

public class UserExpenseNotFoundException extends Exception{
    public UserExpenseNotFoundException() {
    }

    public UserExpenseNotFoundException(String message) {
        super(message);
    }

    public UserExpenseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
