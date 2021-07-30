package com.firstmeridian.persondetails.globalexception;

public class PersonException extends Exception {

    private String message;

    public PersonException(String message) {
        super(message);
        this.message = message;
    }
}
