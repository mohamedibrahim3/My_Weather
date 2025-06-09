package com.example.myweather.logic.model

import kotlinx.datetime.LocalDateTime


data class CurrentWeatherData(
    val cityName: String,
    val currentTemperature: Double,
    val minTemperature: Double,
    val maxTemperature: Double,
    val humidity: Double,
    val windSpeed: Double,
    val rainPercentage: Double,
    val pressure: Double,
    val uvIndex: Double,
    val precipitationProbability: Int,
    val feelsLike: Double,
    val dateTime: LocalDateTime,
    val weatherType: WeatherType
)