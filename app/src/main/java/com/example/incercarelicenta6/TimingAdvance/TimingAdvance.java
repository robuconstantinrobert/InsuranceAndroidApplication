package com.example.incercarelicenta6.TimingAdvance;

public class TimingAdvance {
    private int timingValue;
    private String timestamp;

    public TimingAdvance(int timingValue, String timestamp) {
        this.timingValue = timingValue;
        this.timestamp = timestamp;
    }

    public int getTimingValue() {
        return timingValue;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
