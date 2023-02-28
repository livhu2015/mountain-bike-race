package com.clickatel.raceclub.exception;

public class RiderNotFoundException extends RuntimeException {
    private String msg;
    public RiderNotFoundException(String msg) {
        this.msg = msg;
    }
}