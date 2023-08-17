package com.example.incercarelicenta6.Speed;

public class Speed {
    private int speedValue;
    private String timestamp;

    public Speed(int speedValue, String timestamp) {
        this.speedValue = speedValue;
        this.timestamp = timestamp;
    }

    public int getSpeedValue() {
        return speedValue;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
