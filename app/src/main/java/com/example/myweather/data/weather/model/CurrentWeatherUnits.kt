package com.example.myweather.data.weather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherUnits(
    @SerialName("apparent_temperature")
    val apparentTemperature: String?,
    @SerialName("interval")
    val interval: String?,
    @SerialName("temperature_2m")
    val temperature2m: String?,
    @SerialName("time")
    val time: String?,
    @SerialName("weather_code")
    val weatherCode: String?,
    @SerialName("wind_speed_10m")
    val windSpeed: String?,
    @SerialName("relativehumidity_2m")
    val humidity: String?,
    @SerialName("precipitation")
    val precipitation: String?,
    @SerialName("pressure_msl")
    val pressure: String?,
    @SerialName("uv_index")
    val uvIndex: String?,
)