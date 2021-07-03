package com.weather.home

import android.widget.TextView
import com.weather.R
import com.weather.api.WeatherApiJSON
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class WeatherAdapter(val data: MutableList<WeatherApiJSON>) : RecyclerView.Adapter<GamesViewHolder>() {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.weather_row, parent, false)
        return GamesViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val weather = data[position]
        holder.magic.text = weather.daily[0].weather[0].description
    }
}

class GamesViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    var magic = view.findViewById<TextView>(R.id.city_name)
}