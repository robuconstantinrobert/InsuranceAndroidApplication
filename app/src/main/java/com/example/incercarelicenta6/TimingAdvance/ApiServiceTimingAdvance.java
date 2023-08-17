package com.example.incercarelicenta6.TimingAdvance;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceTimingAdvance {
    @GET("/api/timing")
    Call<List<TimingAdvance>> getObjectList();
}


