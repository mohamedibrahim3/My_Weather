package com.example.myweather.logic.model

data class CurrentWeatherData(
    val apparentTemperature: Double,
    val isDay: Int,
    val precipitationProbability: Int,
    val relativeHumidity2m: Int,
    val surfacePressure: Double,
    val temperature2m: Double,
    val uvIndex: Double,
    val weatherCode: Int,
    val windSpeed10m: Double,
    val cloudCover: Double
) {
    val isDayTime: Boolean
        get() = isDay == 1
}
