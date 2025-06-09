package com.example.myweather.di

import com.example.myweather.logic.usecases.GetWeatherByCoordinateUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetWeatherByCoordinateUseCase(get(),get()) }
}