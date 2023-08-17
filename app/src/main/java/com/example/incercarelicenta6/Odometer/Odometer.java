package com.example.incercarelicenta6.Odometer;

public class Odometer {
    private int odometerValue;
    private String timestamp;

    public Odometer(int odometerValue, String timestamp) {
        this.odometerValue = odometerValue;
        this.timestamp = timestamp;
    }

    public int getOdometerValue() {
        return odometerValue;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
