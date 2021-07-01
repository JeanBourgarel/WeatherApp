package com.weather

import android.app.Application
import com.weather.home.HomeViewModel
import com.weather.nextdays.NextDaysViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val myModule = module {
    viewModel { HomeViewModel() }
    viewModel { NextDaysViewModel() }
}

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(myModule)
        }
    }
}