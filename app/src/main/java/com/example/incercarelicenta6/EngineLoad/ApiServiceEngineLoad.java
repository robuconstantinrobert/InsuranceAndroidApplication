package com.example.incercarelicenta6.EngineLoad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceEngineLoad {
    @GET("/api/engine-load")
    Call<List<EngineLoad>> getObjectList();
}


