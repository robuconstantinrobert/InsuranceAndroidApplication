package com.example.incercarelicenta6.BoostPressure;

import java.sql.Timestamp;

public class BoostPressure {
    private String timestamp;
    private int boostValue;

    public BoostPressure(String timestamp, int boostValue) {
        this.timestamp = timestamp;
        this.boostValue = boostValue;
    }

    public String getTimestamp() {
        return timestamp;
    }
    public int getBoostValue() {
        return boostValue;
    }
}
