package com.example.weatherapp.data.repository

import com.example.weatherapp.data.local.dao.WeatherDao
import com.example.weatherapp.data.local.entity.WeatherEntity
import com.example.weatherapp.data.remote.WeatherApiService
import com.example.weatherapp.data.remote.dto.WeatherResponseDto
import com.example.weatherapp.domain.model.WeatherData
import com.example.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WeatherRepositoryImpl(
    private val api: WeatherApiService,
    private val dao: WeatherDao,
    private val apiKey: String = "YOUR_OPENWEATHER_API_KEY"
) : WeatherRepository {

    // Step 1: Read directly from Room as the Single Source of Truth (SSOT)
    // Whenever Room updates, this Flow automatically emits the fresh value
    override fun getWeather(cityName: String): Flow<WeatherData?> {
        return dao.getWeatherByCity(cityName).map { entity ->
            entity?.toDomain()
        }
    }

    // Step 2 & 3: Fetch fresh network data and save it into Room
    override suspend fun refreshWeather(cityName: String) {
        val remoteDto = api.getCurrentWeather(
            cityName = cityName,
            apiKey = apiKey
        )
        // Insert into Room (Room automatically triggers the Flow in Step 1)
        dao.insertWeather(remoteDto.toEntity())
    }

    // Mapper: DTO -> Local Room Entity
    private fun WeatherResponseDto.toEntity(): WeatherEntity {
        return WeatherEntity(
            cityName = this.cityName,
            temperature = this.main.temp,
            condition = this.weather.firstOrNull()?.mainCondition ?: "Clear",
            humidity = this.main.humidity,
            windSpeed = this.wind.speed
        )
    }

    // Mapper: Local Room Entity -> Domain Model
    private fun WeatherEntity.toDomain(): WeatherData {
        return WeatherData(
            temperature = this.temperature,
            condition = this.condition,
            humidity = this.humidity,
            windSpeed = this.windSpeed
        )
    }
}
