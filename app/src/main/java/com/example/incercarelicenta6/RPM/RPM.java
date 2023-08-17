package com.example.incercarelicenta6.RPM;

public class RPM {
    private int rpmvalue;
    private String timestamp;

    public RPM(int rpmvalue, String timestamp) {
        this.rpmvalue = rpmvalue;
        this.timestamp = timestamp;
    }

    public int getRpmvalue() {
        return rpmvalue;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
