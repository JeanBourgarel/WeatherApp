package com.weather.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.weather.APIRequest
import com.weather.BASE_URL
import com.weather.api.WeatherApiJSON
import com.weather.api.makeApiRequest
import com.weather.cities
import com.weather.databinding.FragmentHomeBinding
import com.weather.nextdays.WeatherNextDaysFragment
import io.uniflow.android.AndroidDataFlow
import io.uniflow.android.livedata.onEvents
import io.uniflow.android.livedata.onStates
import io.uniflow.core.flow.data.UIEvent
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

sealed class HomeState : UIState()
object FetchingData: HomeState()
object Idle: HomeState()

sealed class HomeEvent : UIEvent() {
    data class ClickOnItem(val data: WeatherApiJSON) : HomeEvent()
    data class FetchingFinished(val data: MutableList<WeatherApiJSON>) : HomeEvent()
    object FetchingFailed: HomeEvent()
}

class HomeViewModel: AndroidDataFlow() {
    init {
        action {
            val listWeather: MutableList<WeatherApiJSON> = mutableListOf()
            setState(FetchingData)
            GlobalScope.launch(Dispatchers.IO) {
                for (city in cities) {
                    val result = withContext(Dispatchers.Default) {
                        makeApiRequest(city)
                    }
                    if (result != null) {
                        result.city_name = city.first
                        listWeather.add(result)
                    }
                }
                if (listWeather.size != cities.size) {
                    sendEvent(HomeEvent.FetchingFailed)
                }
                setState(Idle)
                sendEvent(HomeEvent.FetchingFinished(listWeather))
            }
        }
    }

    fun clickOnItem(data: WeatherApiJSON) {
        action {
            sendEvent(HomeEvent.ClickOnItem(data))
        }
    }
}

class HomeFragment: Fragment(), WeatherAdapter.IWeatherRecycler {

    private val HomeViewModel: HomeViewModel by inject()
    lateinit var binding: FragmentHomeBinding
    lateinit var weatherData: MutableList<WeatherApiJSON>
    private val WeatherNextDaysDialog = WeatherNextDaysFragment()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onStates(HomeViewModel) { state ->
            when (state) {
                is FetchingData -> {
                    Log.d("MainActivity", "testfetch")
                    binding.progressBar.isVisible = true
                }
                is Idle -> {
                    binding.progressBar.isVisible = false
                }
            }

        }
        onEvents(HomeViewModel) { event ->
            when (event) {
                is HomeEvent.ClickOnItem -> {
                    val args = Bundle()
                    args.putSerializable("data", event.data)
                    WeatherNextDaysDialog.arguments = args
                    WeatherNextDaysDialog.show(childFragmentManager, "WeatherNextDaysFragment")

                }
                is HomeEvent.FetchingFailed -> {
                    Toast.makeText(requireContext(), "Unable to get weather for some cities, check your internet connection and restart the app.", Toast.LENGTH_LONG).show()
                }
                is HomeEvent.FetchingFinished -> {
                    weatherData = event.data
                    binding.recyclerView.adapter = WeatherAdapter(weatherData, requireContext(), this)
                    println(event.data.size)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun clickOnWeather(weather: WeatherApiJSON) {
        HomeViewModel.clickOnItem(weather)
    }
}