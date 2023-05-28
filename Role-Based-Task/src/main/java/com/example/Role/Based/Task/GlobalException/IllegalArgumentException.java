package com.example.Role.Based.Task.GlobalException;

import org.springframework.stereotype.Component;


public class IllegalArgumentException extends RuntimeException {
    public IllegalArgumentException(String message){
        super(message);
    }
}
