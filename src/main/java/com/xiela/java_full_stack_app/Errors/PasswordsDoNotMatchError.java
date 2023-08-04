package com.xiela.java_full_stack_app.Errors;

public class PasswordsDoNotMatchError extends RuntimeException{
    public PasswordsDoNotMatchError(){
        super("Passwords do not match!");
    }

    public PasswordsDoNotMatchError(String message){
        super(message);
    }

    public PasswordsDoNotMatchError(String message, Throwable cause){
        super(message, cause);
    }

    public PasswordsDoNotMatchError(Throwable cause){
        super("Passwords do not match!", cause);
    }
}
