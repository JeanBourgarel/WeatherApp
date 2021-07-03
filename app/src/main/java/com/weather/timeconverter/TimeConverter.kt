package com.weather.timeconverter

import java.text.SimpleDateFormat
import java.util.*

class TimeConverter {
    fun getDateFromTime(time: Long): String {
        val dateFormat = SimpleDateFormat("EEEE d MMMM YYYY")
        val netDate = Date(time * 1000L)
        return dateFormat.format(netDate)
    }
}