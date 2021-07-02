package com.weather.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.weather.R
import com.weather.api.WeatherApiJSON
import com.weather.databinding.FragmentHomeBinding
import io.uniflow.android.AndroidDataFlow
import io.uniflow.android.livedata.onEvents
import io.uniflow.android.livedata.onStates
import io.uniflow.core.flow.data.UIEvent
import io.uniflow.core.flow.data.UIState
import org.koin.android.ext.android.inject

sealed class HomeState : UIState()
object FetchingData: HomeState()
object Idle: HomeState()
data class Result(val data: String) : HomeState()

sealed class HomeEvent : UIEvent() {
    data class ClickOnItem(val data: String) : HomeEvent()
    object fart: HomeEvent()
//    data class ClickOnItem(val holder: PlayerCardHolder) : HomeEvent()
}

class HomeViewModel: AndroidDataFlow() {
    init {
        action {
            setState(Idle)
        }
    }

    fun fetchingData() {
        action {
            setState(FetchingData)
        }
    }

    fun fetchingFinished() {
        action {
            setState(Idle)
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onEvents(HomeViewModel) { event ->
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tv1.setOnClickListener {
            HomeViewModel.clickOnItem("geh")
//            Toast.makeText(context, "test", Toast.LENGTH_SHORT).show()
        }
/*        binding.newGame.onClick {
            findNavController().navigate(HomeFragmentDirections.launchSelectPlayersFragment())
        }*/
    }
}