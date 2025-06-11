package com.example.myweather.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweather.common.DataState
import com.example.myweather.logic.usecases.GetWeatherByCoordinateUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherByCoordinateUseCase: GetWeatherByCoordinateUseCase
) : ViewModel() {

    private val _weatherUiState: MutableStateFlow<WeatherUiState> =
        MutableStateFlow(WeatherUiState.Loading)
    val weatherUiState: StateFlow<WeatherUiState> = _weatherUiState

    fun getWeatherInfo(lat: Double, long: Double, cityName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getWeatherByCoordinateUseCase(lat, long).collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        updateUiState(WeatherUiState.Success(dataState.data, long, lat, cityName))
                    }
                    is DataState.Error -> {
                        updateUiState(WeatherUiState.Error(dataState.message))
                    }
                    DataState.Loading -> {
                        updateUiState(WeatherUiState.Loading)
                    }
                }
            }
        }
    }

    fun updateUiState(state: WeatherUiState) {
        _weatherUiState.update { state }
    }
}