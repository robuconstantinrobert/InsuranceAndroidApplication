package com.example.incercarelicenta6.IntakeTemp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceIntakeTemp {
    @GET("/api/intake-temp")
    Call<List<IntakeTemp>> getObjectList();
}


