package com.weather.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.weather.R
import com.weather.databinding.FragmentHomeBinding
import io.uniflow.android.AndroidDataFlow

import org.koin.android.ext.android.inject


class HomeViewModel: AndroidDataFlow() {

}

class HomeFragment: Fragment() {

    val HomeViewModel: HomeViewModel by inject()
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}