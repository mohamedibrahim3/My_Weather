package com.example.myweather.data.weather.repository

import com.example.myweather.data.weather.datasource.WeatherRemoteDataSource
import com.example.myweather.data.weather.mapper.WeatherMapper
import com.example.myweather.logic.model.LocationCoordinate
import com.example.myweather.logic.model.Weather
import com.example.myweather.logic.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val weatherMapper: WeatherMapper,
) : WeatherRepository {


    override suspend fun getWeatherByCoordinate(locationCoordinate: LocationCoordinate): Weather {
        val weatherCondition = remoteDataSource.getDailyWeatherByCoordinate(locationCoordinate)
        return weatherMapper.mapWeatherDtoToWeatherEntity(weatherCondition)
    }
}
