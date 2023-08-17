package com.example.incercarelicenta6.FuelLevel;

public class FuelLevel {
    private int fuelValue;
    private String timestamp;

    public FuelLevel(int fuelValue, String timestamp) {
        this.fuelValue = fuelValue;
        this.timestamp = timestamp;
    }

    public int getFuelValue() {
        return fuelValue;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
