package com.example.incercarelicenta6.AbsBarPressure;

public class AbsBarPressure {
    private int absBarPressureValue;
    private String timestamp;

    public AbsBarPressure(int absBarPressureValue, String timestamp)
    {
        this.absBarPressureValue = absBarPressureValue;
        this.timestamp = timestamp;
    }

    public int getAbsBarPressureValue()
    {
        return absBarPressureValue;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

}
