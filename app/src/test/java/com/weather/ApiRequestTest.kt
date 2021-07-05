package com.weather

import com.weather.api.WeatherApiJSON
import com.weather.api.makeApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.Test
import org.hamcrest.CoreMatchers.instanceOf

import org.junit.Assert.*

class ApiRequestTest {
    @Test
    fun testResponseType() {
        val city = Triple("Nantes", 47.1667, -1.5833)
        GlobalScope.launch(Dispatchers.IO) {
            val result = withContext(Dispatchers.Default) {
                makeApiRequest(city)
            }
            assertThat(result, instanceOf(WeatherApiJSON::class.java))
        }
    }
}