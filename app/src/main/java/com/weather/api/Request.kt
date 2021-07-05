package com.weather.api

import android.util.Log
import com.weather.APIRequest
import com.weather.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

suspend fun makeApiRequest(city: Triple<String, Double, Double>): WeatherApiJSON? {
    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(APIRequest::class.java)

    return try {
        api.getWeather(city.second, city.third)
    } catch(e: Exception) {
        Log.e("MainActivity", e.toString())
        null
    }
}