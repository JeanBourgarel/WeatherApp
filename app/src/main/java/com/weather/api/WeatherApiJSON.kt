package com.weather.api

data class WeatherApiJSON(
    val daily: List<Daily>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int,
    var city_name: String
)