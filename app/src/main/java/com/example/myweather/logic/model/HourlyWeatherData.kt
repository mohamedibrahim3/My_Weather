package com.example.myweather.logic.model

import kotlinx.datetime.LocalDateTime


data class HourlyWeatherData(
    val dateTime: LocalDateTime,
    val temperature: Double,
    val weatherType: WeatherType
)
