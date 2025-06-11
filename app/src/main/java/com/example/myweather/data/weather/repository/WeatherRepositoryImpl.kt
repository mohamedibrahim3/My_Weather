package com.example.myweather.data.weather.repository

import com.example.myweather.common.DataState
import com.example.myweather.data.weather.datasource.WeatherDataSource
import com.example.myweather.data.weather.mapper.toWeather
import com.example.myweather.logic.model.Weather
import com.example.myweather.logic.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WeatherRepositoryImpl(
    private val weatherDataSource: WeatherDataSource
) : WeatherRepository {
    override suspend fun getWeatherByCoordinate(
        lat: Double,
        long: Double
    ): Flow<DataState<Weather>> {
        return weatherDataSource.getDailyWeatherByCoordinate(lat, long).map { dataState ->
            when (dataState) {
                is DataState.Loading -> DataState.Loading
                is DataState.Success -> DataState.Success(dataState.data.toWeather())
                is DataState.Error -> DataState.Error(dataState.message)
            }
        }
    }

}
