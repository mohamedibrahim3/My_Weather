package com.example.myweather.logic.model

import kotlinx.datetime.LocalDate


data class DailyWeatherData(
    val date: LocalDate,
    val maxTemp: Double,
    val minTemp: Double,
    val weatherType: WeatherType
)
