package com.example.incercarelicenta6.ThrottlePos;

public class ThrottlePos {
    private int throttleValue;
    private String timestamp;

    public ThrottlePos(int throttleValue, String timestamp) {
        this.throttleValue = throttleValue;
        this.timestamp = timestamp;
    }

    public int getThrottleValue() {
        return throttleValue;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
