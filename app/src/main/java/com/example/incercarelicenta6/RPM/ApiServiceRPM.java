package com.example.incercarelicenta6.RPM;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceRPM {
    @GET("/api/rpm")
    Call<List<RPM>> getObjectList();
}


