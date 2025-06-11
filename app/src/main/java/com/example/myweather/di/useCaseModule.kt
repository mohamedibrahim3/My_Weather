package com.example.myweather.di

import com.example.myweather.logic.usecases.GetWeatherByCoordinateUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::GetWeatherByCoordinateUseCase)
}