package com.example.myweather

import android.app.Application
import android.util.Log
import com.example.myweather.di.dataSourceModule
import com.example.myweather.di.presentationModule
import com.example.myweather.di.repositoryModule
import com.example.myweather.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class WeatherApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(dataSourceModule, repositoryModule, useCaseModule, presentationModule)
        }
        Log.d("WeatherApplication", "Koin started successfully")
    }
}