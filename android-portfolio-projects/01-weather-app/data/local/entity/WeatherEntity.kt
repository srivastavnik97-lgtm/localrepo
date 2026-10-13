package com.example.weatherapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_records")
data class WeatherEntity(
    @PrimaryKey
    val cityName: String,
    val temperature: Double,
    val condition: String,
    val humidity: Int,
    val windSpeed: Double,
    val lastUpdated: Long = System.currentTimeMillis()
)
