package com.example.incercarelicenta6.OilTemp;

public class OilTemp {
    private int oilTempValue;
    private String timestamp;

    public OilTemp(int oilTempValue, String timestamp) {
        this.oilTempValue = oilTempValue;
        this.timestamp = timestamp;
    }

    public int getOilTempValue() {
        return oilTempValue;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
