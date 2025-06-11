package com.example.myweather.logic.model

data class HourlyWeatherData(
    val temperature2m: List<Double>,
    val time: List<String>,
    val weatherCode: List<Int>
)
