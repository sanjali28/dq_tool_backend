package com.app.comparetool.exceptionhandler.CustomException;

public class DatabaseNotFoundException extends RuntimeException{

    public DatabaseNotFoundException(String message){
        super(message);
    }

}
