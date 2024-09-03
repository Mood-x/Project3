package com.example.bank_management.API;

public class ApiException extends RuntimeException{

    public ApiException (String message){
        super(message); 
    }
}
