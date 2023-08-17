package com.example.incercarelicenta6.O2Sensor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceO2Sensor {
    @GET("/api/o2sensor")
    Call<List<O2Sensor>> getObjectList();
}


