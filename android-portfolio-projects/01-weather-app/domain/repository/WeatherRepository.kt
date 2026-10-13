package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.WeatherData
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    // 1. Reactive stream observing local cache
    fun getWeather(cityName: String): Flow<WeatherData?>

    // 2. Network synchronization trigger
    suspend fun refreshWeather(cityName: String)
}
