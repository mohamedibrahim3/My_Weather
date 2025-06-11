package com.example.myweather.data.weather.datasource

import com.example.myweather.common.DataState
import com.example.myweather.data.weather.model.WeatherDto
import kotlinx.coroutines.flow.Flow

interface WeatherDataSource {
    suspend fun getDailyWeatherByCoordinate(
        lat: Double,
        long: Double
    ): Flow<DataState<WeatherDto>>
}