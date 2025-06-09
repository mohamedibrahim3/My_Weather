package com.example.myweather.logic.usecases

import com.example.myweather.logic.model.LocationCoordinate
import com.example.myweather.logic.repository.LocationRepository

class GetCoordinateByLocationUseCase(
    private val locationRepository: LocationRepository
) {
    suspend fun getCoordinateByLocation(): LocationCoordinate {
        return locationRepository.getCurrentLocation()
    }
}