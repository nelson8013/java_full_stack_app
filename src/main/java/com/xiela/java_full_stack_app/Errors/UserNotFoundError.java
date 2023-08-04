package com.xiela.java_full_stack_app.Errors;

public class UserNotFoundError extends RuntimeException {
    public UserNotFoundError() {
        super("User not found");
    }

    public UserNotFoundError(String message) {
        super(message);
    }

    public UserNotFoundError(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundError(Throwable cause) {
        super("User not found", cause);
    }
}
