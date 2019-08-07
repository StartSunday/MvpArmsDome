package com.sun.component.commonsdk.http.entity;

public class ApiException extends Exception{
    public int  code;
    public String message;
    public ApiException(int  code, String message) {
        super(message);
        this.code=code;
        this.message=message;
    }
}
