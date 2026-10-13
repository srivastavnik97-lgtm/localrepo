package com.example.weatherapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.local.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_records WHERE LOWER(cityName) = LOWER(:cityName) LIMIT 1")
    fun getWeatherByCity(cityName: String): Flow<WeatherEntity?>

    @Query("SELECT * FROM weather_records ORDER BY lastUpdated DESC")
    fun getAllSavedWeather(): Flow<List<WeatherEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherEntity)

    @Query("DELETE FROM weather_records WHERE LOWER(cityName) = LOWER(:cityName)")
    suspend fun deleteWeatherByCity(cityName: String)

    @Query("DELETE FROM weather_records")
    suspend fun clearAll()
}
