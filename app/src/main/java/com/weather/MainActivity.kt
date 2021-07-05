package com.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.weather.APIRequest
import com.weather.api.WeatherApiJSON
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

/* TODO
-handle build types
-unit tests
 */

val cities = mutableListOf(
    Triple("Nantes", 47.1667, -1.5833),
    Triple("Quimper", 47.9960325, -4.1024782),
    Triple("Bordeaux", 44.841225, -0.5800364),
    Triple("Lyon", 45.7578137, 4.8320114),
    Triple("Brest", 48.3905283, -4.4860088),
    Triple("Paris", 48.8566969, 2.3514616),
    Triple("Rennes", 48.1113387, -1.6800198),
    Triple("Angers", 47.4739884,-0.5515588),
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MainActivity", "oncreate!!")
    }
}