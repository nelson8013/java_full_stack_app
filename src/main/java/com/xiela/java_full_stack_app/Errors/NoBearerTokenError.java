package com.xiela.java_full_stack_app.Errors;

public class NoBearerTokenError extends RuntimeException {
    public NoBearerTokenError(){
        super("No Bearer Token provided");
    }

    public NoBearerTokenError(String message){
        super(message);
    }

    public NoBearerTokenError(String message, Throwable cause){
        super(message, cause);
    }

    public NoBearerTokenError(Throwable cause){
        super("No Bearer Token provided", cause);
    }
}
