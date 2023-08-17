package com.example.incercarelicenta6.OilTemp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceOilTemp {
    @GET("/api/oil-temp")
    Call<List<OilTemp>> getObjectList();
}


