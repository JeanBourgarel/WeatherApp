package com.weather.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.weather.APIRequest
import com.weather.BASE_URL
import com.weather.api.WeatherApiJSON
import com.weather.cities
import com.weather.databinding.FragmentHomeBinding
import io.uniflow.android.AndroidDataFlow
import io.uniflow.android.livedata.onEvents
import io.uniflow.android.livedata.onStates
import io.uniflow.core.flow.data.UIEvent
import io.uniflow.core.flow.data.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

sealed class HomeState : UIState()
object FetchingData: HomeState()
object Idle: HomeState()

sealed class HomeEvent : UIEvent() {
    data class ClickOnItem(val data: String) : HomeEvent()
    data class FetchingFinished(val data: MutableList<WeatherApiJSON>) : HomeEvent()
}

class HomeViewModel: AndroidDataFlow() {
    init {
        action {
            setState(FetchingData)
            for (city in cities) {
                makeApiRequest(city)
                /*
                data += makeApiRequest
                sendEvent(FetchingFinished(data)
                 */
            }
            setState(Idle)
        }
    }

    private suspend fun makeApiRequest(city: Triple<String, Double, Double>) {
        lateinit var ret: WeatherApiJSON
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response: WeatherApiJSON = api.getWeather(city.second, city.third)

                ret = response
                for ((i, day) in response.daily.withIndex()) {
                    Log.i("MainActivity", city.first + " " + i.toString() + ": " + day.weather[0].description)
                }
                Log.i("MainActivity", response.daily[0].weather[0].description)
            } catch(e: Exception) {
                Log.e("MainActivity", e.toString())
            }
        }
    }

    fun clickOnItem(data: String) {
        action {
            sendEvent(HomeEvent.ClickOnItem(data))
        }
    }
}

class HomeFragment: Fragment() {

    private val HomeViewModel: HomeViewModel by inject()
    lateinit var binding: FragmentHomeBinding
    lateinit var weatherData: MutableList<WeatherApiJSON>
    lateinit var recyclerView : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
                    findNavController().navigate(HomeFragmentDirections.launchNextDaysFragment())
                    Toast.makeText(context, event.data, Toast.LENGTH_SHORT).show()
                }
                is HomeEvent.FetchingFinished -> {
                    //event.data[0].
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        //recyclerView.adapter = GamesAdapter(games, context!!)

        binding.tv1.setOnClickListener {
            HomeViewModel.clickOnItem("test")
        }
    }
}