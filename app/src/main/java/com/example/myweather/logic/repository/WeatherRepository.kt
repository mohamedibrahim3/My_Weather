package com.example.myweather.logic.repository

import com.example.myweather.logic.model.LocationCoordinate
import com.example.myweather.logic.model.Weather

interface WeatherRepository {
    suspend fun getWeatherByCoordinate(locationCoordinate: LocationCoordinate): Weather
}