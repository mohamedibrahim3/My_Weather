package com.example.myweather.di

import com.example.myweather.presentation.screen.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::WeatherViewModel)
}