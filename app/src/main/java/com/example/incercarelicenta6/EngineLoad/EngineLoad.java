package com.example.incercarelicenta6.EngineLoad;

public class EngineLoad {
    private int loadValue;
    private String timestamp;

    public EngineLoad(int loadValue, String timestamp) {
        this.loadValue = loadValue;
        this.timestamp = timestamp;
    }

    public int getLoadValue() {
        return loadValue;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
