package com.example.android2023.domain

import com.example.android2023.data.datasource.remote.response.WeatherResponse
import com.example.android2023.presentation.recyclerview.models.CityItem


interface WeatherRepository {

    suspend fun getWeatherById(id: Int): WeatherResponse

    suspend fun getWeatherByName(q: String): WeatherResponse

    suspend fun getNearCities(
        latitude: Double,
        longitude: Double,
        count: Int
    ): List<CityItem>

}
