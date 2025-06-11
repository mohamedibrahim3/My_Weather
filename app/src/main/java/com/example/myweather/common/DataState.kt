package com.example.myweather.common

sealed class DataState<out T> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val message: String) : DataState<Nothing>()
    data object Loading : DataState<Nothing>()
}