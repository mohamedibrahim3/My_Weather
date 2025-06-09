package com.example.myweather.logic.model

data class Weather(
    val currentWeather: CurrentWeatherData,
    val todayHourly: List<HourlyWeatherData>,
    val weekForecast: List<DailyWeatherData>,
)
