package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.WeatherData

interface WeatherRepository {
    suspend fun getWeather(): WeatherData
}
