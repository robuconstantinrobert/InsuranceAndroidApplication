package com.example.incercarelicenta6.BoostPressure;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceBoostPressure {
    @GET("/api/boost")
    Call<List<BoostPressure>> getObjectList();
}


