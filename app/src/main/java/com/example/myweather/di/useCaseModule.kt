package com.example.myweather.di

import com.example.myweather.logic.usecases.GetCoordinateByLocationUseCase
import com.example.myweather.logic.usecases.GetWeatherByCoordinateUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetCoordinateByLocationUseCase(get()) }
    single { GetWeatherByCoordinateUseCase(get()) }
}