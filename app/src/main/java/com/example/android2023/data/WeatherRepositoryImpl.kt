package com.example.android2023.data

import com.example.android2023.data.datasource.remote.WeatherApi
import com.example.android2023.data.datasource.remote.response.WeatherResponse
import com.example.android2023.domain.WeatherRepository
import com.example.android2023.presentation.recyclerview.models.CityItem
import javax.inject.Inject
import kotlin.math.roundToInt

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {
    override suspend fun getWeatherById(id: Int): WeatherResponse = api.getWeatherById(id)

    override suspend fun getWeatherByName(q: String): WeatherResponse = api.getWeatherByName(q)

    override suspend fun getNearCities(
        latitude: Double,
        longitude: Double,
        count: Int
    ): List<CityItem> = api.getNearCities(latitude, longitude, count).list.map {
        CityItem(
            it.id, it.name, it.main.temp.roundToInt(),
            "https://openweathermap.org/img/w/${it.weather[0].icon}.png"
        )
    }
}
