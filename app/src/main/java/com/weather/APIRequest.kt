package com.weather

import com.weather.api.WeatherApiJSON
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIRequest {
    @GET("onecall?lat=47.1667&lon=-1.5833&exclude=current,minutely,hourly,alerts&appid=e3d8f233e8b8ccbbfce22a77482319ca&units=metric&lang=fr")
    suspend fun gett(): WeatherApiJSON

    @GET("onecall?exclude=current,minutely,hourly,alerts&appid=e3d8f233e8b8ccbbfce22a77482319ca&units=metric&lang=fr")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): WeatherApiJSON
}