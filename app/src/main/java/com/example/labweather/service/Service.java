package com.example.labweather.service;

import com.example.labweather.ResponseWeatherPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("/data/2.5/weather?")
    Call<ResponseWeatherPojo> getWeather(@Query("q") String sity, @Query("appid") String app_id);

}
