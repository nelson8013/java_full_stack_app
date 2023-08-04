package com.xiela.java_full_stack_app.Errors;

public class InvalidCredentialsError extends RuntimeException{
    public InvalidCredentialsError(){
        super("Invalid Credentials");
    }

    public InvalidCredentialsError(String message){
        super(message);
    }

    public InvalidCredentialsError(String message, Throwable cause){
        super(message, cause);
    }

    public InvalidCredentialsError(Throwable cause){
        super("Invalid Credentials provided.", cause);
    }
}
