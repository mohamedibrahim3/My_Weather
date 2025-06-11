package com.example.myweather.data.weather.mapper

import com.example.myweather.data.weather.model.CurrentDto
import com.example.myweather.data.weather.model.CurrentUnitsDto
import com.example.myweather.data.weather.model.DailyDto
import com.example.myweather.data.weather.model.DailyUnitsDto
import com.example.myweather.data.weather.model.HourlyDto
import com.example.myweather.data.weather.model.HourlyUnitsDto
import com.example.myweather.data.weather.model.WeatherDto
import com.example.myweather.logic.model.CurrentUnits
import com.example.myweather.logic.model.CurrentWeatherData
import com.example.myweather.logic.model.DailyUnits
import com.example.myweather.logic.model.DailyWeatherData
import com.example.myweather.logic.model.HourlyUnits
import com.example.myweather.logic.model.HourlyWeatherData
import com.example.myweather.logic.model.Weather

fun WeatherDto.toWeather(): Weather {
    return Weather(
        current = currentDto?.toCurrent() ?: throw IllegalArgumentException("Current data is missing"),
        currentUnits = currentUnitsDto?.toCurrentUnits() ?: throw IllegalArgumentException("Current units data is missing"),
        daily = dailyDto?.toDaily() ?: throw IllegalArgumentException("Daily data is missing"),
        dailyUnits = dailyUnitsDto?.toDailyUnits() ?: throw IllegalArgumentException("Daily units data is missing"),
        hourly = hourlyDto?.toHourly() ?: throw IllegalArgumentException("Hourly data is missing"),
        hourlyUnits = hourlyUnitsDto?.toHourlyUnits() ?: throw IllegalArgumentException("Hourly units data is missing"),
        cityName = "Unknown City"
    )
}

fun CurrentDto.toCurrent(): CurrentWeatherData {
    return CurrentWeatherData(
        apparentTemperature = apparentTemperature ?: 0.0,
        isDay = isDay ?: 0,
        precipitationProbability = precipitationProbability ?: 0,
        relativeHumidity2m = relativeHumidity2m ?: 0,
        surfacePressure = surfacePressure ?: 0.0,
        temperature2m = temperature2m ?: 0.0,
        uvIndex = uvIndex ?: 0.0,
        weatherCode = weatherCode ?: 0,
        windSpeed10m = windSpeed10m ?: 0.0,
        cloudCover = cloudCover ?: 0.0
    )
}

fun CurrentUnitsDto.toCurrentUnits(): CurrentUnits {
    return CurrentUnits(
        apparentTemperature = apparentTemperature ?: "",
        precipitationProbability = precipitationProbability ?: "",
        relativeHumidity2m = relativeHumidity2m ?: "",
        surfacePressure = surfacePressure ?: "",
        temperature2m = temperature2m ?: "",
        windSpeed10m = windSpeed10m ?: "",
        cloudCover = cloudCover ?: ""
    )
}

fun DailyDto.toDaily(): DailyWeatherData {
    return DailyWeatherData(
        temperature2mMax = temperature2mMax?.filterNotNull() ?: emptyList(),
        temperature2mMin = temperature2mMin?.filterNotNull() ?: emptyList(),
        time = time?.filterNotNull() ?: emptyList(),
        weatherCode = weatherCode?.filterNotNull() ?: emptyList()
    )
}

fun DailyUnitsDto.toDailyUnits(): DailyUnits {
    return DailyUnits(
        temperature2mMax = temperature2mMax ?: "",
        temperature2mMin = temperature2mMin ?: ""
    )
}

fun HourlyDto.toHourly(): HourlyWeatherData {
    return HourlyWeatherData(
        temperature2m = temperature2m?.filterNotNull() ?: emptyList(),
        time = time?.filterNotNull() ?: emptyList(),
        weatherCode = weatherCode?.filterNotNull() ?: emptyList()
    )
}

fun HourlyUnitsDto.toHourlyUnits(): HourlyUnits {
    return HourlyUnits(
        temperature2m = temperature2m ?: ""
    )
}