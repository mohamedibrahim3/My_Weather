package com.example.myweather.logic.model

data class DailyWeatherData(
    val temperature2mMax: List<Double>,
    val temperature2mMin: List<Double>,
    val time: List<String>,
    val weatherCode: List<Int>
)
