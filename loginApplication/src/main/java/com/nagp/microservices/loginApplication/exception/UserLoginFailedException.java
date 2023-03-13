package com.nagp.microservices.loginApplication.exception;

public class UserLoginFailedException extends  RuntimeException  {

    public UserLoginFailedException(String message) {
            super(message);
    }
}
