package com.example.weatherapp

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {
    val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val api = retrofit.create(WeatherService::class.java)
}