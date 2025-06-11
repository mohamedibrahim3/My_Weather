package com.example.myweather.data.weather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyDto(
    @SerialName("temperature_2m")
    val temperature2m: List<Double?>?,
    @SerialName("time")
    val time: List<String?>?,
    @SerialName("weather_code")
    val weatherCode: List<Int?>?
)