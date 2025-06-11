package com.example.myweather.presentation.screen

import com.example.myweather.logic.model.Weather


sealed interface WeatherUiState {
    data class Success(
        val weather: Weather,
        val longitude: Double,
        val latitude: Double,
        val cityName: String
    ) : WeatherUiState

    data class Error(val message: String) : WeatherUiState
    data object Loading : WeatherUiState
}