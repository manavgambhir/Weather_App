package com.example.weatherapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/data/2.5/forecast")
    suspend fun getCurrentWeather(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("appid") apiKey:String,
        @Query("units") units:String = "metric"
    ): Response<Weather>

    @GET("/data/2.5/forecast")
    suspend fun getFutureWeather(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("appid") apiKey:String,
        @Query("units") units:String = "metric"
    ): Response<futureWeather>
}