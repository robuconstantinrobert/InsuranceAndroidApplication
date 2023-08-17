package com.example.incercarelicenta6.ThrottlePos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceThrottlePos {
    @GET("/api/throttle")
    Call<List<ThrottlePos>> getObjectList();
}


