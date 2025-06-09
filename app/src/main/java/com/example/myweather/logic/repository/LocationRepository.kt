package com.example.myweather.logic.repository

import com.example.myweather.logic.model.LocationCoordinate

interface LocationRepository {
    suspend fun getCurrentLocation(): LocationCoordinate
}