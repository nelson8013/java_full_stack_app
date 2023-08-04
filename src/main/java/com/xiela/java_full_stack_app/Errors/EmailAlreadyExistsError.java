package com.xiela.java_full_stack_app.Errors;

public class EmailAlreadyExistsError extends RuntimeException{
    public EmailAlreadyExistsError(){
        super("Email Already Exists");
    }
    public EmailAlreadyExistsError(String message){
        super(message);
    }

    public EmailAlreadyExistsError(String message, Throwable cause){
        super(message, cause);
    }

    public EmailAlreadyExistsError(Throwable cause){
        super("Email already exists", cause);
    }
}
