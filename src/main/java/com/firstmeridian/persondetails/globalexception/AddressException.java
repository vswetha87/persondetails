package com.firstmeridian.persondetails.globalexception;

public class AddressException extends Exception {

    private String message;

    public AddressException(String message) {
        super(message);
        this.message = message;
    }
}
