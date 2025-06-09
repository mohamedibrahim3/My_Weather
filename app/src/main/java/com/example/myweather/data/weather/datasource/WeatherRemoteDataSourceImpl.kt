package com.example.myweather.data.weather.datasource

import com.example.myweather.data.weather.model.WeatherDto
import com.example.myweather.logic.model.LocationCoordinate
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.Json

class WeatherRemoteDataSourceImpl(
    private val client: HttpClient,
    private val json: Json
): WeatherRemoteDataSource {
    override suspend fun getDailyWeatherByCoordinate(locationCoordinate: LocationCoordinate): WeatherDto {
        val response =  client.get(BASE_WEATHER_URL) {
            url {
                parameters.append("latitude", locationCoordinate.latitude.toString())
                parameters.append("longitude", locationCoordinate.longitude.toString())
                parameters.append("current", "time,interval,temperature_2m,apparent_temperature,weathercode,wind_speed_10m,relativehumidity_2m,precipitation,pressure_msl,uv_index")
                parameters.append("hourly", "time,temperature_2m,weathercode")
                parameters.append("daily", "time,weathercode,temperature_2m_max,temperature_2m_min")
                parameters.append("timezone", "auto")
            }
        }
        return response.body()
    }

    companion object{
        private const val BASE_WEATHER_URL = "https://api.open-meteo.com/v1/forecast"
    }
}