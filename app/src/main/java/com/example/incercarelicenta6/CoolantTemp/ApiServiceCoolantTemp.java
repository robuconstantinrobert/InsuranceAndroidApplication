package com.example.incercarelicenta6.CoolantTemp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceCoolantTemp {
    @GET("/api/coolant-temp")
    Call<List<CoolantTemp>> getObjectList();
}


