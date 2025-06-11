package com.example.myweather.di

import com.example.myweather.data.weather.repository.WeatherRepositoryImpl
import com.example.myweather.logic.repository.WeatherRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
}