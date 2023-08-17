package com.example.incercarelicenta6.O2Sensor;

public class O2Sensor {
    private int o2SensorValue;
    private String timestamp;

    public O2Sensor(int o2SensorValue, String timestamp) {
        this.o2SensorValue = o2SensorValue;
        this.timestamp = timestamp;
    }

    public int getO2SensorValue() {
        return o2SensorValue;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
