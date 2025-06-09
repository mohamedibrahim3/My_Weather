package com.example.myweather.data.weather.mapper

import com.example.myweather.data.weather.model.WeatherDto
import com.example.myweather.logic.exception.InvalidCityNameException
import com.example.myweather.logic.exception.InvalidDataFoundException
import com.example.myweather.logic.exception.NoWeatherFoundException
import com.example.myweather.logic.model.CurrentWeatherData
import com.example.myweather.logic.model.DailyWeatherData
import com.example.myweather.logic.model.HourlyWeatherData
import com.example.myweather.logic.model.Weather
import com.example.myweather.logic.model.WeatherType
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

class WeatherMapper {

    fun mapWeatherDtoToWeatherEntity(weatherDto: WeatherDto): Weather {
        val currentWeather = weatherDto.currentWeather ?: throw NoWeatherFoundException()
        val hourlyWeather = weatherDto.hourlyWeather ?: throw NoWeatherFoundException()
        val dailyWeather = weatherDto.dailyWeather ?: throw NoWeatherFoundException()
        val cityName = weatherDto.timezone?.split("/")?.lastOrNull() ?: throw InvalidCityNameException()
        val currentTemperature = currentWeather.temperature2m ?: throw NoWeatherFoundException()
        val weatherCode = currentWeather.weatherCode ?: throw NoWeatherFoundException()
        val humidity = currentWeather.humidity ?: throw NoWeatherFoundException()
        val windSpeed = currentWeather.windSpeed ?: throw NoWeatherFoundException()
        val precipitation = currentWeather.precipitation ?: throw NoWeatherFoundException()
        val pressure = currentWeather.pressure ?: throw NoWeatherFoundException()
        val uvIndex = currentWeather.uvIndex ?: throw NoWeatherFoundException()
        val feelsLike =
            currentWeather.apparentTemperature?.toDouble() ?: throw NoWeatherFoundException()
        val time = currentWeather.time ?: throw NoWeatherFoundException()
        val dateTime = parseDateTime(time)
        val hourlyTemperatures = hourlyWeather.temperature2m ?: throw NoWeatherFoundException()
        val hourlyTimes = hourlyWeather.time ?: throw NoWeatherFoundException()
        val hourlyWeatherCodes = hourlyWeather.weatherCode ?: throw NoWeatherFoundException()
        val todayHourly =
            hourlyTemperatures.zip(hourlyTimes.zip(hourlyWeatherCodes)) { temp, pair ->
                val (timeStr, code) = pair
                HourlyWeatherData(
                    dateTime = parseDateTime(timeStr),
                    temperature = temp,
                    weatherType = mapWeatherCode(code)
                )
            }.filter { it.dateTime.date == dateTime.date }
        val dailyMaxTemps = dailyWeather.temperature2mMax ?: throw NoWeatherFoundException()
        val dailyMinTemps = dailyWeather.temperature2mMin ?: throw NoWeatherFoundException()
        val dailyTimes = dailyWeather.time ?: throw NoWeatherFoundException()
        val dailyWeatherCodes = dailyWeather.weatherCode ?: throw NoWeatherFoundException()
        val weekForecast =
            dailyMaxTemps.zip(dailyMinTemps.zip(dailyTimes.zip(dailyWeatherCodes))) { maxTemp, pair ->
                val (minTemp, timeAndCode) = pair
                val (timeStr, code) = timeAndCode
                DailyWeatherData(
                    date = LocalDate.parse(timeStr),
                    maxTemp = maxTemp,
                    minTemp = minTemp,
                    weatherType = mapWeatherCode(code)
                )
            }
        val currentWeatherData = CurrentWeatherData(
            cityName = cityName,
            currentTemperature = currentTemperature,
            minTemperature = dailyMinTemps.firstOrNull() ?: throw NoWeatherFoundException(),
            maxTemperature = dailyMaxTemps.firstOrNull() ?: throw NoWeatherFoundException(),
            humidity = humidity,
            windSpeed = windSpeed,
            rainPercentage = precipitation * 100,
            pressure = pressure,
            uvIndex = uvIndex,
            precipitationProbability = (precipitation * 100).toInt(),
            feelsLike = feelsLike,
            dateTime = dateTime,
            weatherType = mapWeatherCode(weatherCode)
        )
        return Weather(
            currentWeather = currentWeatherData,
            todayHourly = todayHourly,
            weekForecast = weekForecast
        )
    }

    private fun parseDateTime(time: String): LocalDateTime {
        return try {
            LocalDateTime.parse(time)
        } catch (_: Exception) {
            throw InvalidDataFoundException()
        }
    }

    private fun mapWeatherCode(weatherCode: Int): WeatherType {
        return when (weatherCode) {
            0 -> WeatherType.CLEAR_SKY
            1 -> WeatherType.MAINLY_CLEAR
            2 -> WeatherType.PARTLY_CLOUDY
            3 -> WeatherType.OVERCAST
            45 -> WeatherType.FOG
            48 -> WeatherType.DEPOSITING_RIME_FOG
            51 -> WeatherType.DRIZZLE_LIGHT
            53 -> WeatherType.DRIZZLE_MODERATE
            55 -> WeatherType.DRIZZLE_HIGH
            56 -> WeatherType.FREEZING_DRIZZLE_LIGHT
            57 -> WeatherType.FREEZING_DRIZZLE_HEIGHT
            61 -> WeatherType.RAIN_LIGHT
            63 -> WeatherType.RAIN_MODERATE
            65 -> WeatherType.RAIN_HEAVY
            66 -> WeatherType.FREEZING_RAIN_LIGHT
            67 -> WeatherType.FREEZING_RAIN_HIGH
            71 -> WeatherType.SNOW_LIGHT
            73 -> WeatherType.SNOW_MODERATE
            75 -> WeatherType.SNOW_HEAVY
            77 -> WeatherType.SNOW_GRAINS
            80 -> WeatherType.RAIN_SHOWER_LIGHT
            81 -> WeatherType.RAIN_SHOWER_MODRATE
            82 -> WeatherType.RAIN_SHOWER_HEAVY
            85 -> WeatherType.SNOW_SHOWER_LIGHT
            86 -> WeatherType.SNOW_SHOWER_HEAVY
            95 -> WeatherType.THUNDER_STORM
            96 -> WeatherType.THUNDER_STORM_HAIL_LIGHT
            99 -> WeatherType.THUNDER_STORM_HAIL_HEAVY
            else -> WeatherType.UNKNOWN_WEATHER_FORECAST
        }
    }
}