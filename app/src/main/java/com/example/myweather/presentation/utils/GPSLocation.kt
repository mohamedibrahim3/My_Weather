package com.example.myweather.presentation.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class GPSLocation(
    private val context: Context,
    private val fusedLocationClient: FusedLocationProviderClient,
    private val httpClient: HttpClient
) {
    suspend fun getLocation(
        onSuccess: (Location, String) -> Unit,
        onError: (String) -> Unit
    ) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            onError("Location permission not granted")
            return
        }

        try {
            val location = suspendCancellableCoroutine<Location?> { continuation ->
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { loc ->
                        continuation.resume(loc)
                    }
                    .addOnFailureListener { e ->
                        continuation.resumeWithException(e)
                    }
            }

            if (location != null) {
                try {
                    val response = httpClient.get("https://nominatim.openstreetmap.org/reverse") {
                        parameter("format", "json")
                        parameter("lat", location.latitude)
                        parameter("lon", location.longitude)
                        parameter("accept-language", "en")
                        header("User-Agent", "MyWeatherApp/1.0")
                    }
                    if (response.status == HttpStatusCode.OK) {
                        val json = response.body<JsonElement>()
                        val cityName = json.jsonObject["address"]?.jsonObject?.get("city")?.jsonPrimitive?.content
                            ?: json.jsonObject["address"]?.jsonObject?.get("town")?.jsonPrimitive?.content
                            ?: json.jsonObject["display_name"]?.jsonPrimitive?.content
                            ?: "Unknown City"
                        onSuccess(location, cityName)
                    } else {
                        onError("Error fetching city name: ${response.status.value}")
                    }
                } catch (e: Exception) {
                    onError("Error fetching city name: ${e.message ?: "Unknown error"}")
                }
            } else {
                onError("Location not available")
            }
        } catch (e: SecurityException) {
            onError("Location permission error: ${e.message ?: "Unknown error"}")
        } catch (e: Exception) {
            onError(e.message ?: "Unknown error")
        }
    }
}