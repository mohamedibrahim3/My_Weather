package com.example.myweather.logic.usecases

import com.example.myweather.common.DataState
import com.example.myweather.logic.model.Weather
import com.example.myweather.logic.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetWeatherByCoordinateUseCase (
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, long: Double): Flow<DataState<Weather>> {
        return weatherRepository.getWeatherByCoordinate(lat, long)
    }

}