package com.example.myweather.data.weather.datasource

import com.example.myweather.data.weather.model.WeatherDto
import com.example.myweather.logic.model.LocationCoordinate

interface WeatherRemoteDataSource {
    suspend fun getDailyWeatherByCoordinate(locationCoordinate: LocationCoordinate): WeatherDto
}