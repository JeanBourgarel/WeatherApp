package com.weather

import com.weather.timeconverter.TimeConverter
import org.junit.Test

import org.junit.Assert.*

class TimeConverterUnitTest {
    @Test
    fun testTimeConverter() {
        val timeConverter = TimeConverter()
        val output = timeConverter.getDateFromTime(1625483406)
        assertEquals(output, "Monday 5 July 2021")
    }
}