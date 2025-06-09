package com.example.myweather.logic.usecases

import com.example.myweather.logic.model.LocationCoordinate
import com.example.myweather.logic.repository.WeatherRepository

class GetWeatherByCoordinateUseCase (
    private val weatherRepository: WeatherRepository
) {
    suspend fun getWeatherByCoordinate(locationCoordinate: LocationCoordinate) =
        weatherRepository.getWeatherByCoordinate(locationCoordinate)
}