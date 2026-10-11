package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.model.WeatherData
import com.example.weatherapp.domain.repository.WeatherRepository

class GetWeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(): WeatherData {
        return repository.getWeather()
    }
}
