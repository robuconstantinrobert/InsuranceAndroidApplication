package com.example.incercarelicenta6.FuelLevel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceFuelLevel {
    @GET("/api/fuel-level")
    Call<List<FuelLevel>> getObjectList();
}


