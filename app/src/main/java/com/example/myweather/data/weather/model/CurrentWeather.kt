package com.example.myweather.data.weather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather(
    @SerialName("apparent_temperature")
    val apparentTemperature: Double?,
    @SerialName("interval")
    val interval: Int?,
    @SerialName("temperature_2m")
    val temperature2m: Double?,
    @SerialName("time")
    val time: String?,
    @SerialName("weather_code")
    val weatherCode: Int?,
    @SerialName("wind_speed_10m")
    val windSpeed: Double?,
    @SerialName("relativehumidity_2m")
    val humidity: Double?,
    @SerialName("precipitation")
    val precipitation: Double?,
    @SerialName("pressure_msl")
    val pressure: Double?,
    @SerialName("uv_index")
    val uvIndex: Double?,
)