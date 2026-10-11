package com.example.weatherapp.data.repository

import com.example.weatherapp.domain.model.WeatherData
import com.example.weatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl : WeatherRepository {
    override suspend fun getWeather(): WeatherData {
        // Will implement later (API / Database calls)
        TODO("Not yet implemented")
    }
}
