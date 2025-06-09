package com.example.myweather.data.location.repository

import android.annotation.SuppressLint
import android.content.Context
import com.example.myweather.logic.model.LocationCoordinate
import com.example.myweather.logic.repository.LocationRepository
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await

@SuppressLint("MissingPermission")
class LocationRepositoryImpl(
    context: Context
): LocationRepository {
    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    override suspend fun getCurrentLocation(): LocationCoordinate {
        val location = fusedLocationProviderClient.lastLocation.await()

        return location?.let {
            LocationCoordinate(it.latitude, it.longitude)
        } ?: throw IllegalStateException("Location not found")
    }
}