package com.weather.home

import android.content.Context
import android.widget.TextView
import com.weather.R
import com.weather.api.WeatherApiJSON
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView


class WeatherAdapter(val data: MutableList<WeatherApiJSON>, val context: Context, val listener: IWeatherRecycler) : RecyclerView.Adapter<WeatherViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.weather_row, parent, false)
        return WeatherViewHolder(cellForRow, listener)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = data[position]

        holder.city_name.text = weather.city_name
        holder.description.text = weather.daily[0].weather[0].description

        val temperatureText = weather.daily[0].temp.day.toInt().toString() + "° C"
        holder.temperature.text = temperatureText

        val fileName = "icon" + weather.daily[0].weather[0].icon
        val img = context.resources.getIdentifier(fileName, "drawable", context.packageName)
        holder.image_weather.setImageResource(img)

        holder.view.setOnClickListener {
            holder.listener.clickOnWeather(weather)
        }

    }
    interface IWeatherRecycler {
        fun clickOnWeather(weather: WeatherApiJSON)
    }
}

class WeatherViewHolder(val view: View, val listener: WeatherAdapter.IWeatherRecycler): RecyclerView.ViewHolder(view) {
    var image_weather: AppCompatImageView = view.findViewById(R.id.imageview_weather)
    var city_name: TextView = view.findViewById(R.id.textview_city_name)
    var temperature: TextView = view.findViewById(R.id.textview_temperature)
    var description: TextView = view.findViewById(R.id.textview_description)
}