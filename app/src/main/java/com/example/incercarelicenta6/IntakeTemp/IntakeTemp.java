package com.example.incercarelicenta6.IntakeTemp;

public class IntakeTemp {
    private int intakeValue;
    private String timestamp;

    public IntakeTemp(int intakeValue, String timestamp) {
        this.intakeValue = intakeValue;
        this.timestamp = timestamp;
    }

    public int getIntakeValue() {
        return intakeValue;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
