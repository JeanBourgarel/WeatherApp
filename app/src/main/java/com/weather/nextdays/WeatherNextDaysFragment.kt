package com.weather.nextdays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.weather.api.WeatherApiJSON
import com.weather.databinding.FragmentWeatherNextDaysBinding
import com.weather.home.WeatherAdapter
import com.weather.timeconverter.TimeConverter
import io.uniflow.android.AndroidDataFlow
import org.koin.android.ext.android.inject

class WeatherNextDaysViewModel : AndroidDataFlow() {

}

class WeatherNextDaysFragment() : DialogFragment() {

    val WeatherNextDaysViewModel: WeatherNextDaysViewModel by inject()
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

        //val cardHolder = arguments?.getSerializable("cardHolder") as PlayerCardHolder;
    }
}