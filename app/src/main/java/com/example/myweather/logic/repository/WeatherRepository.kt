package com.example.myweather.logic.repository

import com.example.myweather.common.DataState
import com.example.myweather.logic.model.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeatherByCoordinate(lat: Double, long: Double): Flow<DataState<Weather>>
}