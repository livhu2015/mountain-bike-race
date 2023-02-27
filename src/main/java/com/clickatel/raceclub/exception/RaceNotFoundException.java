package com.clickatel.raceclub.exception;

public class RaceNotFoundException extends Throwable {
    private String msg;

    public RaceNotFoundException(String msg) {
        this.msg = msg;
    }
}
