package com.example.incercarelicenta6.Odometer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceOdometer {
    @GET("/api/odometer")
    Call<List<Odometer>> getObjectList();
}


