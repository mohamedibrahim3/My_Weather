package com.example.myweather.logic.model

data class Weather(
    val current: CurrentWeatherData,
    val currentUnits: CurrentUnits,
    val daily: DailyWeatherData,
    val dailyUnits: DailyUnits,
    val hourly: HourlyWeatherData,
    val hourlyUnits: HourlyUnits,
    val cityName: String
)
