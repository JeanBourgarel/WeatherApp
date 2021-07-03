package com.weather.nextdays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.weather.api.WeatherApiJSON
import com.weather.databinding.FragmentWeatherNextDaysBinding

class WeatherNextDaysFragment() : DialogFragment() {

    lateinit var binding: FragmentWeatherNextDaysBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentWeatherNextDaysBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments?.getSerializable("data") as WeatherApiJSON;
        binding.recyclerView2.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView2.adapter = WeatherNextDaysAdapter(data, requireContext())
        binding.textviewCityName.text = data.city_name
    }
}