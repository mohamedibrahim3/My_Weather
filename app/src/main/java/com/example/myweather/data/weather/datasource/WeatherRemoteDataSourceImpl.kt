package com.example.myweather.data.weather.datasource

import com.example.myweather.common.DataState
import com.example.myweather.data.weather.model.WeatherDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class WeatherRemoteDataSourceImpl(
    private val client: HttpClient
): WeatherDataSource {
    override suspend fun getDailyWeatherByCoordinate(
        lat: Double,
        long: Double
    ): Flow<DataState<WeatherDto>> {
        return flow {
            emit(DataState.Loading)
            try {
                val response = client.get(BASE_WEATHER_URL) {
                    parameter("latitude", lat)
                    parameter("longitude", long)
                    parameter(
                        "current",
                        "temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation_probability,wind_speed_10m,weather_code,uv_index,surface_pressure,cloud_cover"
                    )
                    parameter("hourly", "temperature_2m,weather_code")
                    parameter("daily", "weather_code,temperature_2m_max,temperature_2m_min")
                    parameter("timezone", "auto")
                }
                if (response.status == HttpStatusCode.OK) {
                    emit(DataState.Success(response.body<WeatherDto>()))
                } else {
                    emit(DataState.Error("Error: ${response.status.value} - ${response.status.description}"))
                }
            } catch (e: Exception) {
                emit(DataState.Error(e.message ?: "Unknown error"))
            }
        }
    }

    suspend fun getCityName(lat: Double, long: Double): Flow<DataState<String>> {
        return flow {
            emit(DataState.Loading)
            try {
                val response = client.get("https://nominatim.openstreetmap.org/reverse") {
                    parameter("format", "json")
                    parameter("lat", lat)
                    parameter("lon", long)
                    header("User-Agent", "MyWeatherApp/1.0")
                }
                if (response.status == HttpStatusCode.OK) {
                    val json = response.body<JsonElement>()
                    val city = json.jsonObject["address"]?.jsonObject?.get("city")?.jsonPrimitive?.content
                        ?: json.jsonObject["address"]?.jsonObject?.get("town")?.jsonPrimitive?.content
                        ?: json.jsonObject["display_name"]?.jsonPrimitive?.content
                        ?: "Unknown City"
                    emit(DataState.Success(city))
                } else {
                    emit(DataState.Error("Error: ${response.status.value}"))
                }
            } catch (e: Exception) {
                emit(DataState.Error(e.message ?: "Unknown error"))
            }
        }
    }

    companion object {
        private const val BASE_WEATHER_URL = "https://api.open-meteo.com/v1/forecast"
    }
}