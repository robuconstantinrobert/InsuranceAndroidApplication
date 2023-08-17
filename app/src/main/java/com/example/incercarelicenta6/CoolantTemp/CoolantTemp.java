package com.example.incercarelicenta6.CoolantTemp;
public class CoolantTemp {
    private int coolantTempValue;
    private String timestamp;

    public CoolantTemp(String timestamp, int coolantTempValue) {
        this.timestamp = timestamp;
        this.coolantTempValue = coolantTempValue;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getCoolantTempValue() {
        return coolantTempValue;
    }
}
