package com.example.myweather.presentation.utils

object CloudCoverageMapper {

    private val cloudCoverageMap = listOf(
        0.0..10.0 to "Clear Sky",
        11.0..25.0 to "Mostly Clear",
        26.0..50.0 to "Partly Cloudy",
        51.0..75.0 to "Mostly Cloudy",
        76.0..89.0 to "Considerable Cloudiness",
        90.0..100.0 to "Overcast"
    )

    fun getCloudDescription(percentage: Double): String {
        // Ensure percentage is within valid range
        val validPercentage = percentage.coerceIn(0.0, 100.0)

        return cloudCoverageMap.find { (range, _) ->
            validPercentage in range
        }?.second ?: "Unknown"
    }
}