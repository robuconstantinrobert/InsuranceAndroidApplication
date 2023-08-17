package com.example.incercarelicenta6.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServiceUserInfo {
    @GET("/api/user-info")
    Call<List<UserInfo>> getObjectList();
}


