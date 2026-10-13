package com.example.weatherapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherResponseDto(
    @SerializedName("name")
    val cityName: String,
    @SerializedName("main")
    val main: MainDetailsDto,
    @SerializedName("weather")
    val weather: List<WeatherDescriptionDto>,
    @SerializedName("wind")
    val wind: WindDto
)

data class MainDetailsDto(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("humidity")
    val humidity: Int
)

data class WeatherDescriptionDto(
    @SerializedName("main")
    val mainCondition: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)

data class WindDto(
    @SerializedName("speed")
    val speed: Double
)
