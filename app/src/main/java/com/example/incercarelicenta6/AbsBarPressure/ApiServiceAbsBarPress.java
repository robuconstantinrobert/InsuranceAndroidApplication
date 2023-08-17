package com.example.incercarelicenta6.AbsBarPressure;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceAbsBarPress {
    @GET("/api/abs-bar-press")  // Replace with your actual endpoint on the server
    Call<List<AbsBarPressure>> getObjectList();
}


