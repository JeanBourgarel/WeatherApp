package com.weather.nextdays

import android.content.Context
import android.widget.TextView
import com.weather.R
import com.weather.api.WeatherApiJSON
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.weather.timeconverter.TimeConverter


class WeatherNextDaysAdapter(val data: WeatherApiJSON, val context: Context) : RecyclerView.Adapter<WeatherNextDaysViewHolder>() {

    override fun getItemCount(): Int {
        return data.daily.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherNextDaysViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.weather_next_days_row, parent, false)
        return WeatherNextDaysViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: WeatherNextDaysViewHolder, position: Int) {
        val weatherData = data.daily[position]

        holder.date.text = TimeConverter().getDateFromTime(weatherData.dt)
        holder.description.text = weatherData.weather[0].description

        val temperatureText = weatherData.temp.day.toInt().toString() + "° C"
        holder.temperature.text = temperatureText

        val fileName = "icon" + weatherData.weather[0].icon
        val img = context.resources.getIdentifier(fileName, "drawable", context.packageName)
        holder.image_weather.setImageResource(img)

    }
}

class WeatherNextDaysViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    var date: TextView = view.findViewById(R.id.textview_date)
    var image_weather: AppCompatImageView = view.findViewById(R.id.imageview_weather)
    var temperature: TextView = view.findViewById(R.id.textview_temperature)
    var description: TextView = view.findViewById(R.id.textview_description)
}