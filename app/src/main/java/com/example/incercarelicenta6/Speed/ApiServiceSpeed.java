package com.example.incercarelicenta6.Speed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceSpeed {
    @GET("/api/speed")
    Call<List<Speed>> getObjectList();
}


