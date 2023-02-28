package com.clickatel.raceclub.exception;

public class RaceNotFoundException extends RuntimeException {
    private String msg;
    public RaceNotFoundException(String msg) {
        this.msg = msg;
    }
}
