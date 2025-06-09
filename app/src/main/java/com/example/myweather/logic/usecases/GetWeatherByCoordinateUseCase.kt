package com.example.myweather.logic.usecases

import com.example.myweather.logic.model.Weather
import com.example.myweather.logic.repository.LocationRepository
import com.example.myweather.logic.repository.WeatherRepository

class GetWeatherByCoordinateUseCase (
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository
) {
    suspend fun getWeatherByCoordinate(): Weather {
        val location = locationRepository.getCurrentLocation()
        val weather = weatherRepository.getWeatherByCoordinate(location)
        return weather
    }

}