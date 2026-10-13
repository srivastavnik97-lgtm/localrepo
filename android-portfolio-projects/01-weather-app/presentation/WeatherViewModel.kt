package com.example.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DailyForecast(
    val day: String,
    val condition: String,
    val maxTemp: String,
    val minTemp: String
)

data class WeatherHomeUiState(
    val city: String = "Lucknow",
    val temperature: String = "30°C",
    val condition: String = "Sunny",
    val humidity: String = "48%",
    val windSpeed: String = "11 km/h",
    val searchQuery: String = "",
    val weeklyForecast: List<DailyForecast> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class WeatherViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherHomeUiState())
    val uiState: StateFlow<WeatherHomeUiState> = _uiState.asStateFlow()

    init {
        loadWeatherData("Lucknow")
    }

    fun onSearchQueryChanged(newQuery: String) {
        _uiState.update { it.copy(searchQuery = newQuery) }
    }

    fun onSearchClicked() {
        val query = _uiState.value.searchQuery.trim()
        if (query.isNotEmpty()) {
            loadWeatherData(query)
        }
    }

    fun loadWeatherData(cityName: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            _uiState.update {
                it.copy(
                    city = cityName,
                    temperature = "30°C",
                    condition = "Sunny",
                    humidity = "48%",
                    windSpeed = "11 km/h",
                    isLoading = false,
                    weeklyForecast = listOf(
                        DailyForecast("Monday", "Sunny", "31°C", "21°C"),
                        DailyForecast("Tuesday", "Partly Cloudy", "29°C", "20°C"),
                        DailyForecast("Wednesday", "Light Rain", "25°C", "18°C"),
                        DailyForecast("Thursday", "Thunderstorm", "24°C", "17°C"),
                        DailyForecast("Friday", "Clear", "28°C", "19°C"),
                        DailyForecast("Saturday", "Sunny", "32°C", "22°C"),
                        DailyForecast("Sunday", "Cloudy", "27°C", "19°C")
                    )
                )
            }
        }
    }
}
